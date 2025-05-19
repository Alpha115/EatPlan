package com.eat.mypage;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageDAO {
	
	//회원정보 수정
	int member_update(MypageDTO dto);
	
	//닉네임 중복체크
	int nickNameOverlay(String nickname, String user_id);
	
	//이메일 중복체크
	int emailOverlay(String email, String user_id);

}
