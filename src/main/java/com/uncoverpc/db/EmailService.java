package com.uncoverpc.db;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
            // String content = "Dear [[name]],<br>"
            //         + "Please click the link below to verify your registration:<br>"
            //         + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
            //         + "Thank you,<br>"
            //         + "UncoverPC.";
            String content = "<html>\r\n"
            		+ "                    <head>\r\n"
            		+ "                    <link rel='noopener' target='_blank' href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>\r\n"
            		+ "                        <style>\r\n"
            		+ "                            h1, p{\r\n"
            		+ "                                font-family: 'Raleway', Arial, sans-serif;\r\n"
            		+ "                            }\r\n"
            		+ "                            a{\r\n"
            		+ "                                color: white;\r\n"
            		+ "                            }\r\n"
            		+ "                        </style>\r\n"
            		+ "                    </head>\r\n"
            		+ "                    <body style='width: 90%; height: auto; background: rgb(2,0,36); background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(25,3,64,1) 5%, rgba(71,9,121,1) 32%, rgba(44,87,173,1) 61%, rgba(21,151,215,1) 90%, rgba(0,212,255,1) 100%); display: flex; align-items:center; justify-content: center; margin: 0.5rem; padding: 2rem; border-radius: 0.5rem;'>\r\n"
            		+ "                        <div style='background-color: black; color:white; width: 100%; height: 100%; border-radius: 0.25rem; padding: 1rem; margin: auto; text-align: center;'>\r\n"
            		+ "                            <h1>Dear [[name]],</h1>\r\n"
            		+ "                            <p>\r\n"
            		+ "                                Please click the link below to verify your registration:<br>\r\n"
            		+ "                                <h3><a href='[[URL]]' target='_self'>VERIFY</a></h3>\r\n"
            		+ "                                Thank you,<br>\r\n"
            		+ "                                UncoverPC.\r\n"
            		+ "                            </p>\r\n"
            		+ "                        </div>\r\n"
            		+ "                    </body>\r\n"
            		+ "                </html>";
           
             
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
             
            String verifyURL = siteURL + "/verifyEarly?code=" + user.getVerificationCode();
             
            content = content.replace("[[URL]]", verifyURL);
             
            helper.setText(content, true);
             
            mailSender.send(message);
    	} catch(MessagingException | UnsupportedEncodingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
    	}
    }
    
    public void sendResetPasswordEmail(User user, String siteURL) {
    	try {
            String toAddress = user.getEmail();
            String fromAddress = "uncoverpc@gmail.com";
            String senderName = "UncoverPC";
            String subject = "Reset Password";
            String content = "Hello,<br>"
                    + "Please click the link below to reset your password:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">RESET</a></h3>"
                    + "Thank you,<br>"
                    + "UncoverPC.";
             
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
             
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
             
            String verifyURL = siteURL + "/resetPassword?code=" + user.getVerificationCode();
             
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
