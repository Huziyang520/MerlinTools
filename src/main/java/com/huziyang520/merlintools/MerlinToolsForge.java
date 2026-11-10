// Forge entrypoint.

package com.huziyang520.merlintools;

import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class MerlinToolsForge {
    public MerlinToolsForge() {
        MerlinTools.init(null);
    }
}
