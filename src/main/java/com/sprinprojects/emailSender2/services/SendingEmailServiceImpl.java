package com.sprinprojects.emailSender2.services;

import com.sprinprojects.emailSender2.model.MailModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendingEmailServiceImpl implements SendingEmailService {
    private final JavaMailSender emailSender;
    private final Configuration emailConfiguration;

    public SendingEmailServiceImpl(JavaMailSender emailSender, @Qualifier(value = "emailConfigBean") Configuration emailConfiguration) {
        this.emailSender = emailSender;
        this.emailConfiguration = emailConfiguration;
    }

    @Override
    public void sendEmail(MailModel mailModel, String name, String email) throws MessagingException, IOException, TemplateException {
        Map model = new HashMap();
        model.put("name", name);
        model.put("location", mailModel.getLocation());
        model.put("signature", "Adelina");
        model.put("location", "Iasi");
        model.put("content", mailModel.getContent());

        mailModel.setModel(model);

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Template template = emailConfiguration.getTemplate("email.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getModel());
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(mailModel.getSubject());
        mimeMessageHelper.setFrom(mailModel.getFrom());
        emailSender.send(mimeMessage);
    }

    @Override
    public void sendEmails(MailModel mailModel) throws MessagingException, IOException, TemplateException {
        Map<String, String> emails = new HashMap<>();
        emails.put("adelina.apetrei26@gmail.com", "Adelina");
        emails.put("apetreiadelina@gmail.com", "Adelina");
        for(Map.Entry<String, String> email : emails.entrySet()){
            sendEmail(mailModel, email.getValue(), email.getKey());
        }
    }

    public void sendChristmasEmail(MailModel mailModel, String name, String email) throws MessagingException, IOException, TemplateException {
        Map model = new HashMap();
        model.put("name", name);
        model.put("signature", "Adelina");
        model.put("content", mailModel.getContent());

        mailModel.setModel(model);

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Template template = emailConfiguration.getTemplate("christmas.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getModel());
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(mailModel.getSubject());
        mimeMessageHelper.setFrom(mailModel.getFrom());

        emailSender.send(mimeMessage);
    }

    public void sendChristmasEmails(MailModel mailModel) throws MessagingException, IOException, TemplateException {
        Map<String, String> emails = new HashMap<>();
        emails.put("adelina.apetrei26@gmail.com", "Adelina");
        emails.put("apetreiadelina@gmail.com", "Adelina");
        for(Map.Entry<String, String> email : emails.entrySet()){
            sendChristmasEmail(mailModel, email.getValue(), email.getKey());
        }
    }




}
