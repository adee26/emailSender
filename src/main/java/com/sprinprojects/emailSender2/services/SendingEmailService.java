package com.sprinprojects.emailSender2.services;

import com.sprinprojects.emailSender2.model.MailModel;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SendingEmailService {
    void sendEmail(MailModel mailModel, String name, String email) throws MessagingException, IOException, TemplateException;
    void sendEmails(MailModel mailModel) throws MessagingException, IOException, TemplateException;
    void sendChristmasEmails(MailModel mailModel) throws MessagingException, IOException, TemplateException;
    void sendChristmasEmail(MailModel mailModel, String name, String email) throws MessagingException, IOException, TemplateException;

}
