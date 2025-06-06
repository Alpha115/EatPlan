package com.eat.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.dto.BanPeriDTO;
import com.eat.dto.MemberDTO;
import com.eat.dto.UserDTO;

@Service
public class AdMemberService {

	@Autowired
	AdMemberDAO dao;

	// user에게 admin 속성을 부여하는 서비스입니다.
	public boolean addAdmin(String user_id) {
		int row = dao.addAdmin(user_id);
		return row > 0 ? true : false;
	}

	//회원 정지 기능
	public boolean suspend(BanPeriDTO dto) {
		
		// 1.회원 조회
		UserDTO user = dao.findUser(dto.getUser_id());
		if (user == null) 
			return false; // 회원 없으면 정지 불가
		
		// 2. 회원 정지 처리
		try {
		dao.suspendUser(dto); // null = 영구정지
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	// 정지 회원 로그인 막기
	public boolean blockchk(String user_id) {
		int count = dao.blockchk(user_id);
		return count > 0;
	}

	// 회원 리스트 불러오기
	public List<MemberDTO> admember_list(String align, String filter) {
		if(filter.equals("banned")) {
			return dao.adBanMember_list(align);
		}
		return dao.admember_list(align, filter);
	}
	
	
	

}
