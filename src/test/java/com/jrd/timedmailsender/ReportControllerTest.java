package com.jrd.timedmailsender;

import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by jrd on 2016-04-29.
 */
public class ReportControllerTest {
    private Configuration configuration;

    private MailSender mailSender;

    private FileManager fileManager;

    private ReportController reportController;

    @Before
    public void setup() throws IOException {
        configuration = new Configuration("mailSender.properties");
        fileManager = new FileManager();
        mailSender = new MailSender(configuration);
        reportController = new ReportController(configuration, fileManager, mailSender);
    }

    @Test
    public void testSendReportFile() throws IOException, MessagingException {
        reportController.sendReportFile();
    }

    @Test
    public void testCreateReportFile() throws IOException {
        reportController.createReportFile();
    }
}
