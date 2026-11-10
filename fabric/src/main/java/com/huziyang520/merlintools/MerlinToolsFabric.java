// Fabric entrypoint.

package com.huziyang520.merlintools;

import net.fabricmc.api.ModInitializer;

public class MerlinToolsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MerlinTools.init(null);
    }
}
