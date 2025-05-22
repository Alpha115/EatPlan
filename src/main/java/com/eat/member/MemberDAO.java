package com.eat.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eat.dto.MemberDTO;

@Mapper
public interface MemberDAO {
	
	// 로그인
	int login(Map<String, String> params);

	// 회원가입
	int join(MemberDTO dto);

	// 아이디 중복 체크
	int overlayId(String user_id);

	// 닉네임 중복체크
	int overlayNick(String nickname);
	
	// 비밀번호 찾기 요청- 아이디 / 이메일 확인
	int findPassword(@Param("user_id") String user_id, @Param("email")String email);
	
	// 비밀번호 찾기 요청- 아이디 / 이메일 확인
	int updatePassword(@Param("pass")String pass, @Param("user_id") String user_id);

	//프로필 설정
	boolean profileUpload(MemberDTO dto);
	
	//프로필 db저장
	int saveProfileImg(String fileSaved);
	
}
