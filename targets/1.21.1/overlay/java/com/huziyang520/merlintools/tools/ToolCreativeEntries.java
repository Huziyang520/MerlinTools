// Supplies the creative inventory entries for the testing items.

package com.huziyang520.merlintools.tools;

import com.huziyang520.merlintools.Constants;
import com.huziyang520.merlintools.config.ConfigManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

public final class ToolCreativeEntries {
    public static final ResourceKey<CreativeModeTab> TOOLS_TAB =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.withDefaultNamespace("tools_and_utilities"));

    private ToolCreativeEntries() {
    }

    public static List<ItemStack> testWeapons() {
        if (!ConfigManager.server().testingToolkitEnabled()) {
            return List.of();
        }
        List<ItemStack> stacks = new ArrayList<>();
        for (ResourceLocation id : TestWeapons.ids()) {
            TestWeapons.stack(id).ifPresent(stacks::add);
        }
        ItemStack healthEditor = HealthEditorItem.stack();
        if (healthEditor.isEmpty()) {
            Constants.LOG.warn("[MerlinTools] the health editor item is not registered, so it cannot be offered "
                    + "in the creative tab");
        } else {
            stacks.add(healthEditor);
        }
        return stacks;
    }
}
