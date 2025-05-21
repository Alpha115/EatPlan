package com.eat.mypage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MypageDAO {

	// 회원정보 수정
	boolean member_update(MypageDTO dto);

	

	// 회원 정보 수정 - 태그삭제
	void deletMemberTags(@Param("user_id")String user_id,@Param("tagList") List<String> removeTags);

	// 회원 정보 수정 - 태그추가
	void addMemberTags(@Param("user_id") String user_id,@Param("tagList") List<String> addTags);
	
	//프로필 정보 수정
	boolean profile_update(MypageDTO dto);
	
	//사진 정보 DB에 저장
	int saveProfileImg(Map<String, Object>param);

	// 프로필 삭제
	int profile_del(int img_idx);
	
	// 사진 정보 조회
	List<Map<String, String>> photo_del(int img_idx);

	// 프로필 정보 가져오기
	Map<String, String> imgInfo(int img_idx);

}
