package com.sprphnx.mail;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        
		mailService.send(Arrays.asList("toAddress@gmail.com"), "Testing", "my spring boot mail");
		mailService.sendEmailWithAttachment();
	}

}
