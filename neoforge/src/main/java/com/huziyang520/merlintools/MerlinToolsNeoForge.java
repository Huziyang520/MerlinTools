// NeoForge entrypoint.

package com.huziyang520.merlintools;

import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class MerlinToolsNeoForge {
    public MerlinToolsNeoForge() {
        MerlinTools.init(null);
    }
}
