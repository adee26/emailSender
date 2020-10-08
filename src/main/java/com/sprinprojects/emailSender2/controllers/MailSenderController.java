package com.sprinprojects.emailSender2.controllers;

import com.sprinprojects.emailSender2.model.MailModel;
import com.sprinprojects.emailSender2.services.SendingEmailService;
import freemarker.template.TemplateException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
public class MailSenderController {
    private final SendingEmailService sendingEmailService;

    public MailSenderController(SendingEmailService sendingEmailService) {
        this.sendingEmailService = sendingEmailService;
    }
    @PostMapping("/api/v1/send")
    public ResponseEntity<?> sendEmail(@RequestBody MailModel mailModel){
        try{
            sendingEmailService.sendEmail(mailModel, "Adelina", "adelina.apetrei26@gmail.com");
            return ResponseEntity.ok().body(mailModel.toString());
        }catch (MessagingException | TemplateException | IOException e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }


    @PostMapping("api/v2/send")
    public ResponseEntity<?> sendEmails(@RequestBody MailModel mailModel){
        try{
            sendingEmailService.sendEmails(mailModel);
            return ResponseEntity.ok().body(mailModel.toString());
        }catch (MessagingException | TemplateException | IOException e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PostMapping("api/v1/christmas/send")
    public ResponseEntity<?> sendChristmasEmails(@RequestBody MailModel mailModel){
        try{
            sendingEmailService.sendChristmasEmails(mailModel);
            return ResponseEntity.ok().body(mailModel.toString());
        }catch (MessagingException | TemplateException | IOException e){
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}
