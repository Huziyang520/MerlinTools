// Mod Menu integration: gives MerlinTools the config button on the Fabric mods list screen.

package com.huziyang520.merlintools.fabric;

import com.huziyang520.merlintools.tools.ui.ConfigScreens;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            if (!ConfigScreens.available()) {
                return null;
            }
            return ConfigScreens.create(parent);
        };
    }
}
