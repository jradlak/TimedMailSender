package com.jrd.timedmailsender;

/**
 * Created by jrd on 2016-04-28.
 */
public class ReportController {

    private Configuration configuration;

    private FileManager fileManager;

    private MailSender mailSender;

    public ReportController(Configuration configuration, FileManager fileManager, MailSender mailSender) {
        this.configuration = configuration;
        this.fileManager = fileManager;
        this.mailSender = mailSender;
    }

    public void sendReportFile() {

    }

    public void createReportFile() {

    }
}
