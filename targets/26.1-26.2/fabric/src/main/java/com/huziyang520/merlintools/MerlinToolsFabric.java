// Fabric entrypoint: item registration, the two edit payloads and the damage readout.

package com.huziyang520.merlintools;

import com.huziyang520.merlintools.config.ConfigManager;
import com.huziyang520.merlintools.impl.PermissionGate;
import com.huziyang520.merlintools.network.DamageFeedbackPayload;
import com.huziyang520.merlintools.network.HealthEditPayload;
import com.huziyang520.merlintools.network.ItemEditPayload;
import com.huziyang520.merlintools.platform.Services;
import com.huziyang520.merlintools.tools.HealthCeiling;
import com.huziyang520.merlintools.tools.ToolCreativeEntries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MerlinToolsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Services.REGISTRATIONS.bootstrap(null);
        MerlinTools.init(null);

        CreativeModeTabEvents.modifyOutputEvent(ToolCreativeEntries.TOOLS_TAB).register(output -> {
            for (ItemStack stack : ToolCreativeEntries.testWeapons()) {
                output.accept(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        });
        registerPayloads();
    }

    private static void registerPayloads() {
        PayloadTypeRegistry.clientboundPlay().register(DamageFeedbackPayload.TYPE, DamageFeedbackPayload.STREAM_CODEC);
        PayloadTypeRegistry.serverboundPlay().register(HealthEditPayload.TYPE, HealthEditPayload.STREAM_CODEC);
        PayloadTypeRegistry.serverboundPlay().register(ItemEditPayload.TYPE, ItemEditPayload.STREAM_CODEC);

        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (source.getEntity() instanceof ServerPlayer attacker) {
                ServerPlayNetworking.send(attacker, new DamageFeedbackPayload(amount, false, entity.getId()));
            }
            return true;
        });
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) ->
                HealthCeiling.copyTo(oldPlayer, newPlayer));

        ServerPlayNetworking.registerGlobalReceiver(ItemEditPayload.TYPE, (payload, context) -> {
            ServerPlayer player = context.player();
            if (!PermissionGate.canOpenEditor(player)) {
                player.sendSystemMessage(Component.translatable("message.merlintools.no_permission"));
                return;
            }
            if (!payload.stack().isEmpty()) {
                player.setItemInHand(InteractionHand.MAIN_HAND, payload.stack());
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(HealthEditPayload.TYPE, (payload, context) -> {
            ServerPlayer player = context.player();
            if (!PermissionGate.canOpenHealthEditor(player)) {
                player.sendSystemMessage(Component.translatable("message.merlintools.no_permission"));
                return;
            }
            if (player.level().getEntity(payload.targetId()) instanceof LivingEntity target) {
                int max = Math.max(0, payload.max());
                int current = Math.max(0, Math.min(max, payload.current()));
                HealthCeiling.apply(target, max);
                target.setHealth(Math.min(current, target.getMaxHealth()));
            }
        });
    }
}
