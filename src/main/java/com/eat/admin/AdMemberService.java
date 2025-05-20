package com.eat.admin;

import org.springframework.beans.factory.annotation.Autowired;
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

}
