package com.eat.member;

import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	Logger log = LoggerFactory.getLogger(getClass());
		
	// --------------메일보내기-------------- //
	String sendMail(Properties props, Map<String, String> mail) {
		
		log.info("EmailService 가 호출되었습니다.");
		// sender 계정 설정
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail.get("sender"), mail.get("key"));
			}
		});
		
		Message msg=new MimeMessage(session);
		
		try {
			msg.setFrom(new InternetAddress(mail.get("sender")));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.get("receiver")));
			msg.setSubject(mail.get("subject"));
			msg.setText(mail.get("body"));
			
			Transport.send(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Email 발송에 실패하였습니다.");
			return "EmailService가 비정상적으로 종료되었습니다.";
		}
		return "EmailService가 정상적으로 종료되었습니다.";
	}
}
