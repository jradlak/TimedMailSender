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
     * 2. -interval interval of task execution in seconfs
     */
    public static void main(String[] args) {
        long interval = 20;
        String configFile = "mailSender.properties";
        if (args.length > 1) {
            String configFileKey = args[0];
            if (configFileKey.equals("-configFile")) {
                configFile = args[1];
            }
            if (args.length > 3) {
                String intervalKey = args[2];
                if (intervalKey.equals("-interval")) {
                    interval = Long.parseLong(args[3]);
                }
            }
        }

        try {
            reportController = makeReportController(configFile);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }

        Scheduler scheduler = new Scheduler(reportController);
        scheduler.scheduleReport(interval);
    }


    private static ReportController makeReportController(String configFile) throws IOException {
        Configuration configuration = new Configuration(configFile);
        FileManager fileManager = new FileManager();
        MailSender mailSender = new MailSender(configuration);

        reportController = new ReportController(configuration, fileManager, mailSender);
        return  reportController;
    }
}
