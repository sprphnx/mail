package com.sprphnx.mail;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sprphnx.mail.model.Email;
import com.sprphnx.mail.service.MailService;

@SpringBootApplication
public class MailApplication implements CommandLineRunner {

	@Autowired
	MailService mailService;
	
	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        
		List<File> attachments = Arrays.asList(new File(getClass().getClassLoader()
																  .getResource("VR.jpg")
																  .getFile()),
											   new File(getClass().getClassLoader()
													   			  .getResource("VR.jpg")
													   			  .getFile()));
		
		Email email = Email.builder().toAddresses(Arrays.asList("toAddress@gmail.com"))
					   				 .subject("SUB: Testing in progress")
					   				 .message("This is the content of the email.")
					   				 .attachments(attachments)
					   				 .build();
		
		mailService.send(email);	
		
	}

}
