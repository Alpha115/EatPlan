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

	String host = "smtp.gmail.com";
	String port = "587";
	String sender = "levlodia125@gmail.com";
	String key = "zsum oanh jwxb uybi";
	String enable = "true";

	Properties props = new Properties();

	// --------------랜덤 이메일 인증번호 생성-------------- //
	int createNum() {
		randomNum = (int) ((Math.random() * 9000) + 10000); // 5자리 인증번호
		return randomNum;
	}

	// ----------------이메일 전송 기능 -------------------//
	// input type : member의 email을 {"email":""}로 입력
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
	
	
	// --------------------임시 비밀번호 생성 ----------------//
	int createPw() {
		randomNum = (int) ((Math.random() * 9000000) + 10000000); // 8자리 임시비번
		return randomNum;
	}
	
	// --------------------임시 비밀번호 이메일 전송 -------------//
	// USAGE : { "email": [수신자 이메일], "user_id": [유저아이디]}
	@PostMapping("/sendTmp")
	public Map<String, Object> sendTmp(@RequestBody Map<String, String> email){
		resp=new HashMap<String, Object>();
		Map<String, String> mail = new HashMap<String, String>();
		
		int tmpPw=createPw();
		
		String subject = "Eatplan 비밀번호 변경";
		String body = "임시 비밀번호는 " + tmpPw + "입니다. 해당 비밀번호로 로그인해 주세요.";

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
		
		// 임시적으로 비번이 변경되었는지 확인하는 절차
		boolean changed=service.changePwBySystem(tmpPw, email.get("user_id"));
		resp.put("changed", changed);
		
		return resp;
	}
	
	// 임시 비번으로 로그인하고 비번 바꾸라고 alert 띄울까
	
}
