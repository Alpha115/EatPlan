package com.eat.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 관리자를 식별하는 기능입니다.

@Service
public class AdminAuth {
	@Autowired
	AdminMapper mapper;

	public boolean authorization(String user_id) {
		int row = mapper.authorization(user_id);
		return row > 0 ? true : false;
	}

}
