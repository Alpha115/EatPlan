package com.eat.admin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.BanPeriDTO;
import com.eat.dto.MemberDTO;

@CrossOrigin
@RestController
public class AdMemberController {

	@Autowired
	AdMemberService service;

	Map<String, Object> resp = null;
	Logger log = LoggerFactory.getLogger(getClass());

	// 회원 리스트를 출력하는 기능입니다. 관리자 로그인 및 토큰이 필요합니다.
	// 관리자 기준 회원 리스트 출력 기능을 여기서 작성해주세요.
	@GetMapping("/admember_list/{align}/{filter}")
	public Map<String, Object> admember_list(@PathVariable String align, @PathVariable String filter) {
		resp = new HashMap<String, Object>();
		List<MemberDTO> list = service.admember_list(align, filter);
		resp.put("list", list);
		return resp;
	}

	// 회원에게 관리자 권한을 부여하는 기능입니다. 관리자 로그인 및 토큰이 필요합니다.
	@GetMapping("/admember_admin/{user_id}")
	public Map<String, Object> addAdmin(@PathVariable String user_id) {
		resp = new HashMap<String, Object>();
		boolean success = service.addAdmin(user_id);
		resp.put("success", success);
		return resp;
	}

	// 회원의 정지기능을 관리하는 기능입니다. 관리자 로그인 및 토큰이 필요합니다.
	// 정지기능 작성 시 이곳에 작성해주세요.
	@PostMapping(value = "/{user_id}/suspend")
	public Map<String, Object> suspend(@PathVariable String user_id, @RequestBody BanPeriDTO dto) {

		resp = new HashMap<String, Object>();
		dto.setUser_id(user_id);

		boolean success = service.suspend(dto);
		resp.put("success", success);

		return resp;
	}
	
	@GetMapping("/{user_id}/blockchk")
    public Map<String, Object> blockCheck(@PathVariable String user_id) {
        boolean blocked = service.blockchk(user_id);
        return Collections.singletonMap("blocked", blocked);
    }
	

}
