package vn.velacorp;

import vn.velacorp.eventdispatcher.IDispatcher;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Registry {
    private static Properties _prop;

    private static IDispatcher _dispatcher;

    public static Properties getProperties() {
        if (null == _prop) {
            _prop = new Properties();
        }
        return _prop;
    }

    public static Properties loadConfig() throws IOException {
        Properties prop = Registry.getProperties();
        String configPath = new File(".").getCanonicalPath() + "/conf/config.properties";
        prop.load(new FileReader(configPath));
        return prop;
    }

    public static void registerEventDispatcher(IDispatcher dispatcher) {
        _dispatcher = dispatcher;
    }

    public static IDispatcher getEventDispatcher() {
        if (null == _dispatcher) {
            throw new RuntimeException("Unknown event dispatcher, please register it before call Registry.getEventDispatcher");
        }

        return _dispatcher;
    }
}
