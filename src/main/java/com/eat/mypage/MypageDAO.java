package com.eat.mypage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageDAO {
	
	//회원정보 수정
	int member_update(MypageDTO dto);
	
	//회원 정보 수정 - 닉네임 중복체크
	int nickNameOverlay(String nickname, String user_id);
	
	//회원 정보 수정 - 이메일 중복체크
	int emailOverlay(String email, String user_id);
	
	//회원 정보 수정 - 태그삭제
	void deletMemberTags(String user_id, List<Integer> removeTags);
	//회원 정보 수정 - 태그추가
	void addMemberTags(String user_id, List<Integer> addTags);

}
