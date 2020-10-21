package com.example.PostRecommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service("mailService")
public class JavaMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String emailId,String data) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailId);

        msg.setSubject("Recent Activity");
        msg.setText(data);

        javaMailSender.send(msg);

    }


}
