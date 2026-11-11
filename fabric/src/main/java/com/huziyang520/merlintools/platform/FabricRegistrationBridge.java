// Fabric bridge: registers straight into the vanilla registry.

package com.huziyang520.merlintools.platform;

import com.huziyang520.merlintools.platform.services.IRegistrationBridge;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import java.util.function.Supplier;

public class FabricRegistrationBridge implements IRegistrationBridge {
    @Override
    public <T> void register(Registry<T> registry, Identifier id, Supplier<T> value) {
        Registry.register(registry, id, value.get());
    }

    @Override
    public void bootstrap(Object loaderContext) {
    }
}
