// Forge entrypoint of MerlinTools for Minecraft 1.20.1.

package com.huziyang520.merlintools;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class MerlinToolsForge {
    public MerlinToolsForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        if (net.minecraftforge.fml.loading.FMLEnvironment.dist.isClient()) {
            com.huziyang520.merlintools.config.ForgeConfig.install();
            com.huziyang520.merlintools.client.MerlinToolsConfigEntry.register();
        }
        MerlinTools.init(modBus);
    }
}
