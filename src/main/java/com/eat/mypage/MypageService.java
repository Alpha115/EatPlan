package com.eat.mypage;

import java.util.List;
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
	
	// 닉네임 중복체크
	public boolean nickNameOverlay(String nickname, String user_id) {
		int count = dao.nickNameOverlay(nickname, user_id);
		return count > 0;
	}
	
	// 이메일 중복체크
	public boolean emailOverlay(String email, String user_id) {
		int count = dao.emailOverlay(email, user_id);
		return count > 0;
	}

	public boolean member_tag_prefer_update(Map<String, List<Integer>> params, String user_id) {
		List<Integer> removeTags = params.get("remove");
		List<Integer> addTags = params.get("add");
		
		//1. 태그 삭제
		if(removeTags != null && !removeTags.isEmpty()) {
			dao.deletMemberTags(user_id, removeTags);
		}
		
		if(addTags != null && !addTags.isEmpty()) {
			dao.addMemberTags(user_id, addTags);
		}
		
		return true;
	}

	
	
}
