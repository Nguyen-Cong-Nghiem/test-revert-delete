package properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static Properties _prop;

    public ConfigProperties() {
    }

    public static Properties getProperties() {
        if (null == _prop) {
            _prop = new Properties();
        }
        return _prop;
    }

    public static Properties loadConfig() throws IOException {
        Properties prop = ConfigProperties.getProperties();
        String configPath = new File(".").getCanonicalPath() + "/conf/config.properties";
        prop.load(new FileReader(configPath));
        return prop;
    }
}
