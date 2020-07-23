/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaemail;

import javax.mail.Authenticator;
import java.util.Properties;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Yiorgos
 */
public class SendEmail {
    
    public String getRandom() {        
            Random randm = new Random(); 
            int rnumber = randm.nextInt(999999);
            return String.format("%06d", rnumber);       
    }
    
    public boolean sendEmail(User user) throws AddressException {
        boolean test = true; 

        String toEmail = user.getEmail(); 
        String fromEmail = "this.email@gmail.com";
        String password = "qwerty123"; 
                
        try {
            
            Properties prop = new Properties();  
            prop.setProperty("mail.smtp.host", "smtp.mail.com"); 
            prop.setProperty("mail.smtp.port", "587");
            prop.setProperty("mail.smtp.auth", "true"); 
            prop.put("mail.smtp.socketFactory.port", "587"); 
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
            
            Session emailSession = Session.getInstance(prop, new Authenticator() {
                @Override 
                protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
                }
             });
            
            MimeMessage mailMsg = new MimeMessage(emailSession); 
            
            mailMsg.setFrom(fromEmail);
            mailMsg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));

            mailMsg.setSubject("User Mail Verification");
            mailMsg.setText("Registered successfully. Please verify your account using this code: " + user.getCode());
            Transport.send(mailMsg);
            
            test = true; 
           
        }catch(MessagingException e) {
            e.printStackTrace(); 
        }
               
        return test; 
    }

}
