package com.eat.member;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;

	Logger log = LoggerFactory.getLogger(getClass());

	// 로그인
	public boolean login(Map<String, String> params) {
		int cnt = dao.login(params);
		return cnt > 0 ? true : false;
	}

	// 회원가입
	public boolean join(MemberDTO dto) {
		int row = dao.join(dto);
		return row > 0 ? true : false;
	}

	public boolean overlayId(String user_id) {
		int cnt = dao.overlayId(user_id);
		return cnt == 0 ? true : false;
	}

	public boolean overlayNick(String nickname) {
		int cnt = dao.overlayNick(nickname);
		return cnt == 0 ? true : false;
	}

}
