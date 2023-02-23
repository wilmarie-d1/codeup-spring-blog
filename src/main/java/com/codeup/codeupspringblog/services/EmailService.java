package com.codeup.codeupspringblog.services;

import com.codeup.codeupspringblog.models.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {
    private JavaMailSender emailSender;
    public EmailService(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }
    @Value("${spring.mail.from}")
    private String from;

    public void prepareAndSend(Ad ad, String subject, String body){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(ad.getUser().getEmail());
    }
}
