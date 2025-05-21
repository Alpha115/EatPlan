package com.eat.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

@Service
public class AdMemberService {

	@Autowired
	AdMemberDAO dao;

	// user에게 admin 속성을 부여하는 서비스입니다.
	public boolean addAdmin(String user_id) {
		int row = dao.addAdmin(user_id);
		return row > 0 ? true : false;
	}

	//회원 정지 기능
	public boolean suspend(String user_id, int days) {
		
		// 1.회원 조회
		User user = dao.findUser(user_id);
		if (user == null) {
			return false; // 회원 없으면 정지 불가
		}
		
		//2.정지 기간
		Date start = new Date();
		
		
		
		// 3. 회원 정지 처리
		try {
		dao.suspendUser(user_id,null); // null = 영구정지
		return true;
		}catch (Exception e) {
			// db처리 실패 시 false 반환
			return false;
		}
		
		
		
		
		
		
		
	}

}
