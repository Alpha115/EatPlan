package com.eat.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eat.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	int login(Map<String, String> params);

	int join(MemberDTO dto);

	int overlayId(String user_id);

	int overlayNick(String nickname);

//	boolean profileUpload(MemberDTO dto);
//
//	int saveProfileImg(String fileSaved);
	
}
