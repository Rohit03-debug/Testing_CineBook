package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPERTIES = new Properties();
    private static boolean loaded;

    private ConfigReader() {
    }

    public static synchronized void init() {
        if (loaded) {
            return;
        }
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new IllegalStateException("config.properties was not found in src/test/resources");
            }
            PROPERTIES.load(inputStream);
            loaded = true;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load config.properties", e);
        }
    }

    public static String get(String key) {
        init();
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue.trim();
        }
        String value = PROPERTIES.getProperty(key);
        return value == null ? "" : value.trim();
    }

    public static String get(String key, String defaultValue) {
        String value = get(key);
        return value.isBlank() ? defaultValue : value;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        return value.isBlank() ? defaultValue : Boolean.parseBoolean(value);
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value.isBlank()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String baseUrl() {
        return get("baseUrl").replaceAll("/+$", "");
    }

    public static boolean isPlaceholderCredential(String value) {
        return value == null || value.isBlank() || value.startsWith("REPLACE_WITH_");
    }
}
