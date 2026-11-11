// A loader supplied store for MerlinTools's switches.

package com.huziyang520.merlintools.config;

public interface ConfigBackend {
    ConfigReader view();
    void write(String key, String value);
    void save();
    String name();
}
