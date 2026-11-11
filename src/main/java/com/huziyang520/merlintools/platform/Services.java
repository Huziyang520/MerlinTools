// Locates the loader specific implementation of a service interface.

package com.huziyang520.merlintools.platform;

import com.huziyang520.merlintools.platform.services.IRegistrationBridge;

public final class Services {
    public static final IRegistrationBridge REGISTRATIONS = ForgeRegistrationBridge.INSTANCE;

    private Services() {
    }
}
