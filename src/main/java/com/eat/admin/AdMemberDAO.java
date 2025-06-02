package com.eat.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eat.dto.BanPeriDTO;
import com.eat.dto.MemberDTO;
import com.eat.dto.UserDTO;


@Mapper
public interface AdMemberDAO {

	// user에게 admin속성을 부여하는 함수이나, toggle 기능을 DB에서 처리합니다.
	int addAdmin(String user_id);

	//회원 정지
	void suspendUser(BanPeriDTO dto);
	
	//회원 조회
	UserDTO findUser(String user_id);
	
	// 정지 회원 로그인 막기
	int blockchk(String user_id);

	// 회원 리스트 불러오기
	List<MemberDTO> admember_list(String align, String filter);

	
	

}
