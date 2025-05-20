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
	
	//프로필 사진 수정
	void fileUpdate(int img_idx, String ori_filename, String new_filename);
	
	// 새 이미지를 DB에 넣기
	void fileInsert(String ori_filename, String new_filename);
	
	// 넣은 이미지의 img_idx 가져오기
	int getinsertImgidx();
	
	// 회원 테이블에 새 img_idx 저장
	void updateMemberImgIdx(String user_id, int newImg_Idx);

}
