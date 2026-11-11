// Loader independent bootstrap of MerlinTools.

package com.huziyang520.merlintools;

import com.huziyang520.merlintools.config.ConfigManager;
import com.huziyang520.merlintools.mixin.RangedAttributeAccessor;
import com.huziyang520.merlintools.platform.Services;
import com.huziyang520.merlintools.tools.HealthEditorItem;
import com.huziyang520.merlintools.tools.TestWeapons;
import com.huziyang520.merlintools.tools.macro.MacroStorage;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;

public final class MerlinTools {
    private static boolean initialized;

    private MerlinTools() {
    }

    public static void init(Object loaderContext) {
        if (initialized) {
            return;
        }
        initialized = true;

        Constants.LOG.info("{} is starting", Constants.MOD_NAME);
        Services.REGISTRATIONS.bootstrap(loaderContext);
        com.huziyang520.merlintools.network.ServerSender.register();
        ConfigManager.reload();
        widenAttributeCeilings();
        TestWeapons.register();
        HealthEditorItem.register();
        MacroStorage.load();
    }

    private static void widenAttributeCeilings() {
        raiseCeiling(Attributes.MAX_HEALTH, "max_health");
        raiseCeiling(Attributes.ATTACK_DAMAGE, "attack_damage");
    }

    private static void raiseCeiling(Attribute attribute, String name) {
        if (attribute instanceof RangedAttributeAccessor accessor) {
            accessor.merlintools$setMaxValue(Integer.MAX_VALUE);
            Constants.LOG.info("[MerlinTools] the {} ceiling was raised to the integer limit", name);
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
