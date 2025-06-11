package com.eat.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eat.dto.MemberDTO;
import com.eat.dto.TagPreferDTO;

@Mapper
public interface MemberDAO {
	
	// 로그인
	int login(Map<String, String> params);
	
	//관리자 계정여s
	int authorization(String user_id);
	
	// 회원가입
	int join(MemberDTO dto);
	int joinTag(TagPreferDTO[] tags);

	// 아이디 중복 체크
	int overlayId(String user_id);

	// 닉네임 중복체크
	int overlayNick(String nickname);
	
	// 비밀번호 찾기 요청- 아이디 / 이메일 확인
	int findPassword(@Param("user_id") String user_id, @Param("email")String email);
	
	// 비밀번호 찾기 요청- 아이디 / 이메일 확인
	int updatePassword(@Param("pass")String pass, @Param("user_id") String user_id);

	//프로필 설정
	int profileUpload(MemberDTO dto);
	
	//프로필 db저장
	int saveProfileImg(Map<String, Object> param);
	
	//user_id -> nickname으로 보이게 하는것
	MemberDTO getMemberId(@Param("user_id") String user_id);

	String findUserIdByNickname(String nickname);

	// 탈퇴 유저 로그인 제어
	int withdraw_check(String user_id);

	// 사진 삭제 요청
	int profileImage_delete(int img_idx);

	
}
