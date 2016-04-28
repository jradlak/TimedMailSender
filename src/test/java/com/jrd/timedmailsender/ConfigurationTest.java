package com.jrd.timedmailsender;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jakub on 27.04.16.
 */
public class ConfigurationTest {

    private final static String mailServerAddres = "smtp.google.com";

    @Test
    public void configTest() throws IOException {
        Configuration configuration = new Configuration("mailSender.properties");
        String server = configuration.getProperty("mail.smtp.host");
        Assert.assertTrue(mailServerAddres.equals(server));
    }
}
