package com.sprphnx.mail.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sprphnx.mail.model.Email;

@Service
public class MailService {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public void send(Email email) throws MessagingException, IOException {
		if(email.getAttachments()!=null && email.getAttachments().size()>0) {
			sendEmailWithAttachment(email);
		} else {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

			simpleMailMessage.setTo(email.getToAddresses().toArray(new String[0]));
	        simpleMailMessage.setSubject(email.getSubject());
	        simpleMailMessage.setText(email.getMessage());

	        javaMailSender.send(simpleMailMessage);
		}
	}
	
	public void sendEmailWithAttachment(Email email) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		
        helper.setTo(email.getToAddresses().toArray(new String[0]));

        helper.setSubject(email.getSubject());

        helper.setText(email.getMessage(), true);

        email.getAttachments().forEach(attachment->{
			try {
				helper.addAttachment(attachment.getName(), attachment);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        
        javaMailSender.send(msg);

    }

}
