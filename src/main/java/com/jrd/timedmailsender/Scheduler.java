package com.jrd.timedmailsender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by jakub on 27.04.16.
 */
public class Scheduler {

    private Logger LOGGER = Logger.getLogger(FileManager.class.getName());


    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ReportController reportController;

    public Scheduler(ReportController reportController, long period) {
        this.reportController = reportController;
    }

    public ScheduledFuture<?> scheduleReport(long period) {
        final Runnable reporter = new Runnable() {
            public void run() {
                try {
                    reportController.sendReportFile();
                    reportController.createReportFile();
                } catch (IOException e) {
                    LOGGER.info(e.getMessage());
                } catch (MessagingException e) {
                    LOGGER.info(e.getMessage());
                }
            }
        };

        final ScheduledFuture<?> reportHandle = scheduler.scheduleAtFixedRate(reporter,
                period, period, SECONDS);

        return reportHandle;
    }
}
