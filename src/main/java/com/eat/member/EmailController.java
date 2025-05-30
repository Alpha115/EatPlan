package com.eat.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class EmailController {

	@Autowired
	EmailService service;
	Map<String, Object> resp = null;
	int randomNum = -1;

	// -------------- email service 에 필요한 properties -----------------//
//	String propPath="email.properties";
//	Properties props=new Properties();

	String host = "smtp.gmail.com";
	String port = "587";
	String sender = "levlodia125@gmail.com";
//	String receiver = "goldenapple123@naver.com"; // 수신자 예시
	String key = "zsum oanh jwxb uybi";
	String enable = "true";

	Properties props = new Properties();

	// --------------랜덤 이메일 인증번호 생성-------------- //
	int createNum() {
		randomNum = (int) ((Math.random() * 9000) + 10000); // 5자리 인증번호
		return randomNum;
	}

	// ----------------이메일 전송 기능 -------------------//
	// input type : member의 email을 String으로 입력
	@PostMapping("/sendNumber")
	public Map<String, Object> sendEmail(@RequestBody Map<String, String> email) throws Exception {
		resp = new HashMap<String, Object>();
		Map<String, String> mail = new HashMap<String, String>();

		String subject = "Eatplan 이메일 인증 요청";
		String body = "인증번호 : \n\n" + createNum();

		mail.put("sender", sender);
		mail.put("receiver", email.get("email")); // post를 통해 입력받은 유저 이메일
		mail.put("key", key);
		mail.put("subject", subject);
		mail.put("body", body);

		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", port);
		props.setProperty("mail.smtp.auth", enable);
		props.setProperty("mail.smtp.starttls.enable", enable);

		// tls protocol 버전이 맞지 않아서 이거 추가함
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");

		resp.put("result", service.sendMail(props, mail));

		return resp;
	}

	// ----------------이메일 확인 기능 -------------------//
	@GetMapping("/checkNumber/{number}")
	public Map<String, Object> checkEmail(@PathVariable int number) {
		resp = new HashMap<String, Object>();
		boolean checked = false;
		if (number == randomNum) {
			checked = true;
			randomNum = (int) ((Math.random() * 900) + 1000); // 번호초기화
		} else {
			
		}
		resp.put("success", checked);
		return resp;
	}
}
