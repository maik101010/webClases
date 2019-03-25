package com.pruebas.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	
	public void setMailServerProperties() {
		String emailPort = "587";//gmail's smtp port
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");
		emailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	}

	public int createEmailMessage(String email) throws AddressException,
			MessagingException {
		//String[] toEmails = { "maiksoft10@gmail.com" };
		//String toEmails ="maiksoft10@gmail.com";
		String asuntoEmail = "Aplicación web java";
		int codigoValidacion = (int)(1000000 * Math.random());
		String cuerpoEmail = "Este Email es enviado para recuperar su contraseña. El código para recuperar su "
				+ "contraseña es "+codigoValidacion;

		mailSession = Session.getDefaultInstance(emailProperties, null);
		/**
		 * Arreglar excepcion could not convert socket to tls
		 */
		mailSession.getProperties().put("mail.smtp.starttls.enable", "true");
		emailMessage = new MimeMessage(mailSession);
		

		emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		emailMessage.setSubject(asuntoEmail);
		emailMessage.setContent(cuerpoEmail, "text/html");//for a html email
		/**
		 * Retornamos el codigo para ser validado
		 */
		return codigoValidacion;
	}

	public void sendEmail() throws AddressException, MessagingException {

		String emailHost = "smtp.gmail.com";
		String fromUser = "nuevo12345test";//just the id alone without @gmail.com
		String fromUserEmailPassword = "nuevo(10)";
		Transport transport = mailSession.getTransport("smtp");		
		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email enviado exitosamente.");
	}

}