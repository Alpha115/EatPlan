package com.eat.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class EmailController {

	Map<String, Object> resp = null;
	
	// -------------- email service 에 필요한 properties 불러오기 -----------------//
	String propPath="email.properties";
	Properties props=new Properties();

	private String receiver = "goldenapple123@naver.com"; // 수신자 예시
	
	// --------------랜덤 이메일 인증번호 생성--------------
	public int createNum() {
		return (int) ((Math.random()*9000)+10000); //5자리 인증번호
	}
	

	// ----------------이메일 전송 기능 -------------------//
	@PostMapping("/sendEmail")
	public Map<String, Object> sendEmail(@RequestBody String email) {
		resp = new HashMap<String, Object>();
		String subject = "Test 이메일 보내기";
		String body = "이메일 테스트입니다.";
		
		return resp;
	}
}
