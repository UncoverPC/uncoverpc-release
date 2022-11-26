package com.uncoverpc.db;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.uncoverpc.model.user.EarlyUser;
import com.uncoverpc.model.user.User;

@Service
public class EmailService {
	
	private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);
	
	@Autowired
	private UserService userService;
    
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendVerificationEmail(User user, String siteURL){
    	try {
            String toAddress = user.getEmail();
            String fromAddress = "uncoverpc@gmail.com";
            String senderName = "UncoverPC";
            String subject = "Please verify your registration";
            String content = "Dear [[name]],<br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                    + "Thank you,<br>"
                    + "UncoverPC.";
             
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
             
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
             
            content = content.replace("[[name]]", user.getFullName());
            String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
             
            content = content.replace("[[URL]]", verifyURL);
             
            helper.setText(content, true);
             
            mailSender.send(message);
    	} catch(MessagingException | UnsupportedEncodingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
    	}
    }
    
    @Async
    public void sendEarlyVerificationEmail(EarlyUser user, String siteURL){
    	try {
            String toAddress = user.getEmail();
            String fromAddress = "uncoverpc@gmail.com";
            String senderName = "UncoverPC";
            String subject = "Please verify your registration";
            String content = "Hello,<br>"
            		+ "Thank you for your interest in UncoverPC.<br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                    + "Thank you,<br>"
                    + "UncoverPC.";
             
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
             
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
             
            String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
             
            content = content.replace("[[URL]]", verifyURL);
             
            helper.setText(content, true);
             
            mailSender.send(message);
    	} catch(MessagingException | UnsupportedEncodingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
    	}
    }
    
    public boolean verify(String verificationCode) {
        User user = userService.findByVerificationCode(verificationCode);
         
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userService.save(user);
             
            return true;
        }
         
    }

}
