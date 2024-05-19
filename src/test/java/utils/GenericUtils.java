package utils;

public class GenericUtils {

    public static String getApiVersion(String apiConfig, String propertyName) {
        return ConfigUtils.getGenericElement(apiConfig, propertyName);
    }

    public static String getBaseUrl(String apiConfig, String protocolPropertyName,
                                    String baseUrlPropertyName) {
        return ConfigUtils.getGenericElement(apiConfig, protocolPropertyName) +
                "://" + ConfigUtils.getGenericElement(apiConfig, baseUrlPropertyName);
    }
}
