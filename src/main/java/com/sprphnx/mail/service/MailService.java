package com.sprphnx.mail.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public void send(List<String> toList, String subject, String message) {
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toList.toArray(new String[0]));
        
        msg.setSubject(subject);
        msg.setText(message);

        javaMailSender.send(msg);
	}
	
	public void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
        helper.setTo("toaddress@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        helper.setText("<h1>Check attachment for image!</h1>", true);

        helper.addAttachment("VR.jpg", new ClassPathResource("VR.jpg"));

        javaMailSender.send(msg);

    }

}
