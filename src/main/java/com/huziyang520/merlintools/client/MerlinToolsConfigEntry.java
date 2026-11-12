// Registers the mod list's Config button, which Forge only draws for a mod that registered one.

package com.huziyang520.merlintools.client;

import com.huziyang520.merlintools.Constants;
import com.huziyang520.merlintools.config.ForgeConfig;
import com.huziyang520.merlintools.tools.ui.ConfigScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;

public final class MerlinToolsConfigEntry {
    private MerlinToolsConfigEntry() {
    }

    public static void register() {
        if (!ForgeConfig.available()) {
            Constants.LOG.info("[MerlinTools] no configuration screen is installed; the switches stay editable "
                    + "in config/MerlinTools/client.toml");
            return;
        }
        try {
            ModLoadingContext.get().registerExtensionPoint(
                    ConfigScreenHandler.ConfigScreenFactory.class,
                    () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> {
                        Screen screen = ConfigScreens.create(parent);
                        return screen == null ? parent : screen;
                    }));
            Constants.LOG.info("[MerlinTools] the mod list's Config button is registered");
        } catch (Throwable failure) {
            Constants.LOG.warn("[MerlinTools] the mod list's Config button could not be registered", failure);
        }
    }
}
