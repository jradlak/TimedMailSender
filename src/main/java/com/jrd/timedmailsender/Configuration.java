package com.jrd.timedmailsender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jakub on 27.04.16.
 */
public class Configuration {

    public static class Keys {
        public static final String mail_smtp_auth= "mail.smtp.auth";
        public static final String mail_smtp_starttls_enable = "mail.smtp.starttls.enable";
        public static final String mail_smtp_host = "mail.smtp.host";
        public static final String mail_smtp_port = "mail.smtp.port";
        public static final String mail_from = "mail.from";
        public static final String mail_to = "mail.to";
        public static final String mail_subject = "mail.subject";
        public static final String mail_username = "mail.username";
        public static final String mail_password = "mail.password";
        public static final String file_template = "file.template";
        public static final String file_report_path = "file.report.path";
    }

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
