package com.jrd.timedmailsender;

import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by jrd on 2016-04-28.
 */
public class MailSenderTest {

    private Configuration configuration;

    private MailSender mailSender;

    private FileManager fileManager;

    @Before
    public void setup() throws IOException {
        configuration = new Configuration("mailSender.properties");
        fileManager = new FileManager();
        mailSender = new MailSender(configuration, fileManager);
    }

    @Test
    public void testSendMail() throws MessagingException {
        mailSender.sendMail("Test message", configuration.getProperty(Configuration.Keys.file_report_path) + "\\raport_testowy.txt");
    }
}
