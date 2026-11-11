// The Forge events MerlinTools subscribes to on the game event bus.

package com.huziyang520.merlintools;

import com.huziyang520.merlintools.network.DamageFeedbackPayload;
import com.huziyang520.merlintools.network.ServerSender;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class MerlinGameEvents {
    private MerlinGameEvents() {
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity().level().isClientSide()) {
            return;
        }
        if (event.getSource().getEntity() instanceof ServerPlayer attacker) {
            ServerSender.sendToPlayer(attacker, new DamageFeedbackPayload(
                    event.getAmount(), false, event.getEntity().getId()));
        }
    }
}
