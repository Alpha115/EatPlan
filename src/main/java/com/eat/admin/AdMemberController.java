package com.eat.admin;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AdMemberController {
	
	@Autowired AdMemberService service;
	
	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());
	
	// 회원 리스트를 출력하는 기능
	
	
	// 회원에게 관리자 권한을 부여하는 기능입니다. 관리자 로그인 및 토큰이 필요합니다.
	@GetMapping("/admember_admin/{user_id}")
	public Map<String, Object> addAdmin(@PathVariable String user_id){
		resp=new HashMap<String, Object>();
		boolean success=service.addAdmin(user_id);
		resp.put("success", success);
		return resp;
	}

}
