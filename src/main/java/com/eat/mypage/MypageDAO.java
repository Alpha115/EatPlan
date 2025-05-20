package com.eat.mypage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageDAO {

	// 회원정보 수정
	boolean member_update(MypageDTO dto);

	

	// 회원 정보 수정 - 태그삭제
	void deletMemberTags(String user_id, List<String> removeTags);

	// 회원 정보 수정 - 태그추가
	void addMemberTags(String user_id, List<String> addTags);
	
	//프로필 정보 수정
	boolean profile_update(MypageDTO dto);
	
	//사진 정보 DB에 저장
	int saveProfileImg(String fileSaved);

}
