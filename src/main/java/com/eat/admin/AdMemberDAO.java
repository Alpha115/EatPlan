package com.eat.admin;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdMemberDAO {

	// user에게 admin속성을 부여하는 함수이나, toggle 기능을 DB에서 처리합니다.
	int addAdmin(String user_id);

	//회원 정지
	void suspendUser(BanPeriDTO dto);
	
	//회원 조회
	UserDTO findUser(String user_id);

	
	

}
