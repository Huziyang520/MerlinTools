// Locates the loader specific implementation of a service interface.

package com.huziyang520.merlintools.platform;

import com.huziyang520.merlintools.Constants;
import com.huziyang520.merlintools.platform.services.IRegistrationBridge;
import java.util.ServiceLoader;

public class Services {
    public static final IRegistrationBridge REGISTRATIONS = load(IRegistrationBridge.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz, Services.class.getClassLoader())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
