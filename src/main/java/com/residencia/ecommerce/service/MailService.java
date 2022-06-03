package com.residencia.ecommerce.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Value("${spring.mail.host}")
	private String mailHost;
	
	@Value("${spring.mail.port}")
	private String mailPort;
	
	@Value("${spring.mail.username}")
	private String mailUserName;
	
	@Value("${spring.mail.password}")
	private String mailPassword;
	
	@Autowired
	JavaMailSender emailSender;

	@Autowired
	MailProperties  mailProperties;
	
	public MailService(JavaMailSender javaEmailSender) {
		this.emailSender = javaEmailSender;
	}
	
	public void enviarEmail(String destinatarioEmail, String assunto, String mensagemEmail) {
		SimpleMailMessage sMailMessage = new SimpleMailMessage();
		
		sMailMessage.setTo(destinatarioEmail);
		sMailMessage.setSubject(assunto);
		sMailMessage.setText(mensagemEmail);
		//Cuidado no momento de usar um servidor real, para setar um remetente valido abaixo
		sMailMessage.setFrom("g5serratec20221@gmail.com");
		
		emailSender.send(sMailMessage);
	}
	
	public void enviarEmailHTML(String destinatarioEmail, String assunto, String mensagemEmail) throws MessagingException{
	        MimeMessage mimeMessage = emailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	        
	        
	        helper.setTo(destinatarioEmail);
	        helper.setSubject(assunto);
	        helper.setText(mensagemEmail, true);
	        
	        helper.setFrom("g5serratec20221@gmail.com");
	        
	        emailSender.send(mimeMessage);
	}
}
