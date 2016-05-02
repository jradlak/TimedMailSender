package com.jrd.timedmailsender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by jrd on 2016-04-28.
 */
public class ReportController {

    private static Logger LOGGER = Logger.getLogger(ReportController.class.getName());

    private Configuration configuration;

    private FileManager fileManager;

    private MailSender mailSender;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public ReportController(Configuration configuration, FileManager fileManager, MailSender mailSender) {
        this.configuration = configuration;
        this.fileManager = fileManager;
        this.mailSender = mailSender;
    }

    public void sendReportFile() throws IOException, MessagingException {
        String pathLastReport = configuration.getProperty(Configuration.Keys.file_report_path);
        String templateFileName = configuration.getProperty(Configuration.Keys.file_template);
        String latestReport = fileManager.getLatestReportFile(pathLastReport);
        String template = fileManager.getTemplateFile(templateFileName);

        LOGGER.info("sendReportFile, latest report = " +latestReport);
        mailSender.sendMail(prepareMailMessage(template), latestReport);
    }

    public void createReportFile() throws IOException {
        String headerFileName = configuration.getProperty(Configuration.Keys.file_header);
        String reportFolderPath = configuration.getProperty(Configuration.Keys.file_report_path);
        LOGGER.info("createReportFile");
        fileManager.createReportFile(headerFileName, reportFolderPath);
    }

    private String prepareMailMessage(String template) {
        return template.replace("@date", sdf.format(new Date()));
    }
}