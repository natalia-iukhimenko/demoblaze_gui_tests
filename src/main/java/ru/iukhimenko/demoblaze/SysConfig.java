package ru.iukhimenko.demoblaze;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import static ru.iukhimenko.demoblaze.SysConfig.PROPERTY_PATH;

@SysConfig.Sources({
      //  "system:properties",
        "classpath:" + PROPERTY_PATH
})
public interface SysConfig extends Config {
    String PROPERTY_PATH = "config.properties";
    SysConfig CONFIG = ConfigFactory.create(SysConfig.class);

    String baseUrl();

    String apiBaseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("isRemote")
    Boolean isRemote();

    @Key("remoteUrlChrome")
    @DefaultValue("http://localhost:4444/wd/hub")
    String remoteUrlChrome();

    @Key("remoteUrlFirefox")
    @DefaultValue("http://localhost:4445/wd/hub")
    String remoteUrlFirefox();
}
