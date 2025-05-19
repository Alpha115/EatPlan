package com.eat.mypage;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MypageService {

	@Autowired MypageDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//회원정보 수정
	public boolean member_update(MypageDTO dto) {
		int row = dao.member_update(dto);
		return row > 0;
	}

	
	
}
