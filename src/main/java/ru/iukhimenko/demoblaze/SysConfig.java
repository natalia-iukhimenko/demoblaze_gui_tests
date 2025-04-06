package ru.iukhimenko.demoblaze;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import static ru.iukhimenko.demoblaze.SysConfig.PROPERTY_PATH;

@SysConfig.Sources("classpath:" + PROPERTY_PATH)
public interface SysConfig extends Config {
    String PROPERTY_PATH = "config.properties";
    SysConfig CONFIG = ConfigFactory.create(SysConfig.class, System.getProperties());

    String baseUrl();

    String apiBaseUrl();

    @DefaultValue("chrome")
    String browser();

    Boolean isRemote();

    @DefaultValue("http://localhost:4444/wd/hub")
    String remoteUrlChrome();

    @DefaultValue("http://localhost:4445/wd/hub")
    String remoteUrlFirefox();
}
