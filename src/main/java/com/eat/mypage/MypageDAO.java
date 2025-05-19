package com.eat.mypage;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageDAO {
	
	//회원정보 수정
	int member_update(MypageDTO dto);

}
