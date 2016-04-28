package com.jrd.timedmailsender;

import java.io.*;
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
}
