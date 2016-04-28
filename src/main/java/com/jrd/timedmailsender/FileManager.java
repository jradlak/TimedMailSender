package com.jrd.timedmailsender;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by jakub on 27.04.16.
 */
public class FileManager {

    private Logger LOGGER = Logger.getLogger(FileManager.class.getName());

    private File file;

    private BufferedWriter writer;

    private BufferedReader reader;

    public String getTemplateFile(String fileName) throws IOException {
        String result = "";
        file = new File(fileName);
        reader = new BufferedReader(new FileReader(file));
        String line = "";
        do {
           line = reader.readLine();
           if (line != null) {
               result += line + "\n";
           }
        } while (line != null);

        reader.close();
        return result;
    }



    public String getLatestReportFile(String directoryPath) {
        String ext = "csv";
        LOGGER.info("!getTheNewestFileName.agregatedFilePath = " + directoryPath);
        String filePath = directoryPath

        File theNewestFile = null;
        File dir = new File(filePath);
        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
        File[] files = dir.listFiles(fileFilter);

        if (files != null && files.length > 0) {
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];
        }

        if (theNewestFile != null) {
            this.agregatedFileName = this.agregatedFilePath + theNewestFile.getName();
        }
    }
}
