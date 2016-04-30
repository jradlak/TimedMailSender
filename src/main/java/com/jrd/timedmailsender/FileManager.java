package com.jrd.timedmailsender;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by jakub on 27.04.16.
 */
public class FileManager {

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
        String filePath = directoryPath;

        File theNewestFile = null;
        File dir = new File(filePath);
        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
        File[] files = dir.listFiles(fileFilter);

        if (files != null && files.length > 0) {
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];
        }

        String result = "";
        if (theNewestFile != null) {
            result = directoryPath + "/" + theNewestFile.getName();
        }

        return result;
    }

    public void createReportFile(String headerFileName, String reportFolderPath) throws IOException {
        String headerText = getHeaderText(headerFileName);
        String fileName = reportFolderPath + "/report" + + (new Date()).getTime() + ".csv";
        file = new File(fileName);
        writer = new BufferedWriter(new FileWriter(file, true));
        writer.append(headerText);
        writer.close();
    }

    private String getHeaderText(String fileName) throws IOException {
        file = new File(fileName);
        reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        return line;
    }
}
