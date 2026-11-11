// Wiring of the optional configuration screens on Fabric.

package com.huziyang520.merlintools.config;

import com.huziyang520.merlintools.Constants;
import com.huziyang520.merlintools.tools.ui.ConfigScreens;

public final class FabricConfig {
    private static final String CLOTH_API = "me.shedaniel.clothconfig2.api.ConfigBuilder";

    private FabricConfig() {
    }

    public static boolean install() {
        if (clothPresent()) {
            ConfigManager.setBackend(new TomlBackend("cloth"));
            ConfigScreens.install(ClothScreen::create);
            Constants.LOG.info("[MerlinTools] Cloth Config API found: the configuration screen edits client.toml");
            return true;
        }
        Constants.LOG.info("[MerlinTools] Cloth Config is not installed: client.toml is read directly and "
                + "/merlintools config cannot open a screen");
        return false;
    }

    private static boolean clothPresent() {
        try {
            Class.forName(CLOTH_API);
            return true;
        } catch (ClassNotFoundException | LinkageError exception) {
            return false;
        }
    }
}
