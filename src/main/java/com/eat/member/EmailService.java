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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.admin.AdminMapper;

@Service
public class EmailService {

	@Autowired
	AdminMapper mapper;

	// --------------메일보내기-------------- //
	String sendMail(Properties props, Map<String, String> mail) {

		// sender 계정 설정
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail.get("sender"), mail.get("key"));
			}
		});

		Message msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress(mail.get("sender")));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.get("receiver")));
			msg.setSubject(mail.get("subject"));
			msg.setText(mail.get("body"));

			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
			return "이메일 발송에 실패했습니다.";
		}
		return "이메일을 발송했습니다.";
	}

	boolean changePwBySystem(int tmpPw, String user_id) {
		int row = mapper.changePw(tmpPw, user_id);
		return row > 0 ? true : false;
	}

}
