package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigUtils {

    public static String getGenericElement(String configFile,
                                           String propertyName,
                                           String defaultValue) {
        String results = defaultValue;
        try {
            Properties appProp = new Properties();
            appProp.load(Files.newInputStream(Paths.get(configFile)));
            results = appProp.getProperty(propertyName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    public static String getGenericElement(String configFile, String propertyName) {
        return getGenericElement(configFile, propertyName, "");
    }
}
