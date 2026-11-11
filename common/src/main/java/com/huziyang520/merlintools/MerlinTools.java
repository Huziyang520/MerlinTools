// Loader independent bootstrap of MerlinTools.

package com.huziyang520.merlintools;

import com.huziyang520.merlintools.config.ConfigManager;
import com.huziyang520.merlintools.tools.HealthEditorItem;
import com.huziyang520.merlintools.tools.TestWeapons;
import com.huziyang520.merlintools.tools.macro.MacroStorage;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import com.huziyang520.merlintools.mixin.RangedAttributeAccessor;

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

    private static void raiseCeiling(Holder<Attribute> attribute, String name) {
        if (attribute.value() instanceof RangedAttributeAccessor accessor) {
            accessor.merlintools$setMaxValue(Integer.MAX_VALUE);
            Constants.LOG.info("[MerlinTools] the {} ceiling was raised to the integer limit", name);
        }
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
