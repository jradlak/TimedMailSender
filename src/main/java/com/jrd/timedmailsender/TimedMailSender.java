package com.jrd.timedmailsender;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by jakub on 27.04.16.
 */
public class TimedMailSender {
    private static Logger LOGGER = Logger.getLogger(MailSender.class.getName());

    private static Configuration configuration;

    private static FileManager fileManager;

    private static MailSender mailSender;

    private static ReportController reportController;

    /*
     * Arguments:
     * 1. -configFile : smtp mail configuration file name. If absent program will use file in his home folder
     *
     */
    public static void main(String[] args) {
        long period = 1000;

        try {
            reportController = makeReportController();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }

        Scheduler scheduler = new Scheduler(reportController, period);
        scheduler.scheduleReport(period);
    }

    private static ReportController makeReportController() throws IOException {
        Configuration configuration = new Configuration("mailSender.properties");
        FileManager fileManager = new FileManager();
        MailSender mailSender = new MailSender(configuration);

        reportController = new ReportController(configuration, fileManager, mailSender);
        return  reportController;
    }
}
