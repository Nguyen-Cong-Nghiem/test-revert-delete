package vn.shippo.rider.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.velacorp.Registry;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {
    private static Logger logger = LoggerFactory.getLogger(Internationalization.class.getName());
    private static String language;
    private static String country;
    private static Locale locale;

    private static Internationalization instance;

    public Internationalization() {
        try {
            language = Registry.getProperties().getProperty("language", "en");
            country = Registry.getProperties().getProperty("country","US");
            locale = new Locale(language, country);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static Internationalization getInstance() {
        if (instance == null) {
            instance = new Internationalization();
        }
        return instance;
    }

    public ResourceBundle getBundle() {
        return getBundle("i18n/MessagesBundle");
    }

    public ResourceBundle getBundle(String baseName) {
        return ResourceBundle.getBundle(baseName, locale);
    }

}
