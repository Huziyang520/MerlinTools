// Loader independent bootstrap of MerlinTools.

package com.huziyang520.merlintools;

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
    }
}
