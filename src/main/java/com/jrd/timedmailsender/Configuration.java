package com.jrd.timedmailsender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jakub on 27.04.16.
 */
public class Configuration {

    Properties properties;

    public Configuration(String propertiesFileName) throws IOException {
        properties = new Properties();
        String propFileName = propertiesFileName;

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
