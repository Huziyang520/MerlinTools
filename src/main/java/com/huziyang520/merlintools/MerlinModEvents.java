// The Forge events MerlinTools subscribes to on the mod event bus.

package com.huziyang520.merlintools;

import com.huziyang520.merlintools.tools.ToolCreativeEntries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class MerlinModEvents {
    private MerlinModEvents() {
    }

    @SubscribeEvent
    public static void onBuildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (ToolCreativeEntries.TOOLS_TAB.equals(event.getTabKey())) {
            for (ItemStack stack : ToolCreativeEntries.testWeapons()) {
                addOnce(event, stack);
            }
            return;
        }
    }

    private static void addOnce(BuildCreativeModeTabContentsEvent event, ItemStack stack) {
        if (stack.isEmpty()) {
            return;
        }
        if (event.getEntries().contains(stack)) {
            Constants.LOG.debug("[MerlinTools] {} is already in the creative tab, not adding it twice", stack);
            return;
        }
        event.accept(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
