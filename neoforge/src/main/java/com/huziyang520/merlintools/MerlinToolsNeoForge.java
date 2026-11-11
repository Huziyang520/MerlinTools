// NeoForge entrypoint: item registration, the two edit payloads, the damage readout and the config files.

package com.huziyang520.merlintools;

import com.huziyang520.merlintools.config.ConfigManager;
import com.huziyang520.merlintools.config.NeoConfigs;
import com.huziyang520.merlintools.impl.PermissionGate;
import com.huziyang520.merlintools.network.DamageFeedbackPayload;
import com.huziyang520.merlintools.network.HealthEditPayload;
import com.huziyang520.merlintools.network.ItemEditPayload;
import com.huziyang520.merlintools.platform.Services;
import com.huziyang520.merlintools.tools.HealthCeiling;
import com.huziyang520.merlintools.tools.ToolCreativeEntries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@Mod(Constants.MOD_ID)
public class MerlinToolsNeoForge {
    public MerlinToolsNeoForge(IEventBus eventBus, ModContainer modContainer) {
        eventBus.addListener(this::onBuildCreativeTab);
        eventBus.addListener(this::onRegisterPayloads);
        eventBus.addListener(this::onConfigLoading);
        eventBus.addListener(this::onConfigReloading);
        NeoForge.EVENT_BUS.addListener(this::onIncomingDamage);
        NeoForge.EVENT_BUS.addListener(this::onPlayerClone);
        NeoConfigs.register(modContainer);
        Services.REGISTRATIONS.bootstrap(eventBus);
        MerlinTools.init(eventBus);
    }

    private void onConfigLoading(ModConfigEvent.Loading event) {
        adopt(event.getConfig());
    }

    private void onConfigReloading(ModConfigEvent.Reloading event) {
        adopt(event.getConfig());
    }

    private static void adopt(ModConfig config) {
        if (config.getType() == ModConfig.Type.CLIENT) {
            ConfigManager.setBackend(NeoConfigs.clientBackend());
        } else if (config.getType() == NeoConfigs.sharedType()) {
            ConfigManager.setServerBackend(NeoConfigs.commonBackend());
        }
    }

    private void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToClient(DamageFeedbackPayload.TYPE, DamageFeedbackPayload.STREAM_CODEC);
        event.registrar("1").playToServer(ItemEditPayload.TYPE, ItemEditPayload.STREAM_CODEC,
                (payload, context) -> {
                    if (!(context.player() instanceof ServerPlayer player)) {
                        return;
                    }
                    if (!PermissionGate.canOpenEditor(player)) {
                        player.sendSystemMessage(Component.translatable("message.merlintools.no_permission"));
                        return;
                    }
                    if (!payload.stack().isEmpty()) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, payload.stack());
                    }
                });
        event.registrar("1").playToServer(HealthEditPayload.TYPE, HealthEditPayload.STREAM_CODEC,
                (payload, context) -> {
                    if (context.player() instanceof ServerPlayer player) {
                        applyHealthEdit(player, payload);
                    }
                });
    }

    private static void applyHealthEdit(ServerPlayer player, HealthEditPayload payload) {
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
    }

    private void onPlayerClone(PlayerEvent.Clone event) {
        HealthCeiling.copyTo(event.getOriginal(), event.getEntity());
    }

    private void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer attacker) {
            attacker.connection.send(new DamageFeedbackPayload(
                    event.getAmount(), false, event.getEntity().getId()));
        }
    }

    private void onBuildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (!ToolCreativeEntries.TOOLS_TAB.equals(event.getTabKey())) {
            return;
        }
        for (ItemStack stack : ToolCreativeEntries.testWeapons()) {
            if (event.getParentEntries().contains(stack) || event.getSearchEntries().contains(stack)) {
                continue;
            }
            event.accept(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
