package com.jrd.timedmailsender;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jrd on 2016-04-28.
 */
public class FileManagerTest {

    private Configuration configuration;

    private FileManager fileManager;

    @Before
    public void setup() throws IOException {
        configuration = new Configuration("mailSender.properties");
        fileManager = new FileManager();
    }

    @Test
    public void getTemplateFileTest() throws IOException {
        String fileTemplate = fileManager.getTemplateFile(configuration.getProperty(Configuration.Keys.file_template));
        Assert.assertTrue(!fileTemplate.isEmpty());
    }
}
