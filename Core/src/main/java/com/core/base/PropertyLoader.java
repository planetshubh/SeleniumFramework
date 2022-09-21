package com.core.base;

import com.core.constants.CoreConstants;
import com.core.exceptions.PropertyFileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class PropertyLoader {
    private static Properties property = new Properties();
    private static final Map<String, String> CONFIGMAP = new HashMap<>();

    //Private constructor to avoid external instantiation
    private PropertyLoader() {
    }

    static {
        try (FileInputStream file = new FileInputStream(CoreConstants.getConfigFilePath())) {
            property.load(file);
            for (Map.Entry<Object, Object> entry : property.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim()); //remove the trailing and leading spaces
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String getProp(String key) {
        if (CONFIGMAP.get(key).isEmpty() || Objects.isNull(key)) {
            throw new PropertyFileException("Property '" + key + "' is not found or empty. Please check config.properties.");
        }
        return CONFIGMAP.get(key.toLowerCase());
    }
}
