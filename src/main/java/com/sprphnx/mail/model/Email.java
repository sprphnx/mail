package com.sprphnx.mail.model;

import java.io.File;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Email {
	
	private List<String> toAddresses;
	private List<String> ccAddresses;
	
	private String subject;
	private String message;
	
	private List<File> attachments;

}
