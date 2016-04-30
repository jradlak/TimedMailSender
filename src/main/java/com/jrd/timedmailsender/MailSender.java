package com.jrd.timedmailsender;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by jakub on 27.04.16.
 */
public class MailSender {

    private Logger LOGGER = Logger.getLogger(MailSender.class.getName());

    private Configuration configuration;

    public MailSender(Configuration configuration) {
        this.configuration = configuration;
    }

    public void sendMail(String messageStr, String attachmentFileName) throws MessagingException {
        final String username = configuration.getProperty(Configuration.Keys.mail_username);
        final String password = configuration.getProperty(Configuration.Keys.mail_password);

        Properties props = new Properties();
        props.put("mail.smtp.auth", configuration.getProperty(Configuration.Keys.mail_smtp_auth));
        props.put("mail.smtp.starttls.enable", configuration.getProperty(Configuration.Keys.mail_smtp_starttls_enable));
        props.put("mail.smtp.host", configuration.getProperty(Configuration.Keys.mail_smtp_host));
        props.put("mail.smtp.port", configuration.getProperty(Configuration.Keys.mail_smtp_port));
        props.put("mail.smtp.ssl.trust", configuration.getProperty(Configuration.Keys.mail_smtp_host));

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(configuration.getProperty(Configuration.Keys.mail_from)));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(configuration.getProperty(Configuration.Keys.mail_to)));
        message.setSubject(configuration.getProperty(Configuration.Keys.mail_subject));

        setMessageContent(message, messageStr, attachmentFileName);

        Transport.send(message);

        LOGGER.info("Mail sended");
    }

    private void setMessageContent(Message message, String messageStr, String attachmentFileName) throws MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(messageStr);
        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        String filename = attachmentFileName;
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);
    }
}