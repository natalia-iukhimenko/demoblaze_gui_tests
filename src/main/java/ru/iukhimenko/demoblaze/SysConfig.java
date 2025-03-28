package ru.iukhimenko.demoblaze;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import static ru.iukhimenko.demoblaze.SysConfig.PROPERTY_PATH;

@SysConfig.Sources("classpath:" + PROPERTY_PATH)
public interface SysConfig extends Config {
    String PROPERTY_PATH = "config.properties";
    SysConfig CONFIG = ConfigFactory.create(SysConfig.class);

    String baseUrl();
    String apiBaseUrl();
    String browser();
    String environment();
    @DefaultValue("false")
    String isHeadless();
}
