package com.eat.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eat.dto.MemberDTO;
import com.eat.utils.JwtUtil;

@CrossOrigin
@RestController
public class MemberController {

	@Autowired
	MemberService service;

	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());

	// 로그인
	@PostMapping(value = "/login")
	public Map<String, Object> login(
			@RequestBody Map<String, String> params) {
		resp = new HashMap<String, Object>();
		boolean success = service.login(params);
		resp.put("success", success);
		if (success == true) {
			String token = JwtUtil.getToken("id", params.get("id"));
			resp.put("token", token);
		}
		return resp;
	}

	// 회원가입
	@PostMapping(value = "/join")
	public Map<String, Object> join(@RequestBody MemberDTO dto) {
		resp = new HashMap<String, Object>();
		boolean success = service.join(dto);
		resp.put("success", success);
		return resp;
	}

	// 아이디 중복 체크
	@GetMapping(value="/overlay/id/{user_id}")
	public Map<String, Object> overlayId(@PathVariable String user_id) {
		resp = new HashMap<String, Object>();
		boolean success = service.overlayId(user_id);
		resp.put("use", success);
		return resp;
	}
	
	// 닉네임 중복 체크
	@GetMapping(value="/overlay/nickname/{nickname}")
	public Map<String, Object> overlayNick(@PathVariable String nickname) {
		resp = new HashMap<String, Object>();
		boolean success = service.overlayNick(nickname);
		resp.put("use", success);
		return resp;
	}
	
	// 프로필 사진 변경
	@PutMapping(value="/profileUpload")
	public Map<String, Object> profileUpload(@RequestParam(required = false) MultipartFile[] files,
			MemberDTO dto) {
		resp = new HashMap<String, Object>();
		boolean success = service.profileUpload(files, dto);
		resp.put("success", success);
		resp.put("img_idx", dto.getImg_idx());
		return resp;
	}

	//비밀번호 찾기 요청 - 아이디 / 이메일 확인
		@PostMapping(value ="/findPassword")
		public Map<String, Object>finedPassword(@RequestBody MemberDTO dto){
			
			resp = new HashMap<String, Object>();
			boolean success = service.findPassword(dto.getUser_id(), dto.getEmail());
			resp.put("success", success);
			return resp;
		}
		
		//비밀번호 찾기 요청 - 비밀번호 바꾸기
		@PutMapping(value="/updatePassword")
		public Map<String, Object>updatePassword(@RequestBody MemberDTO dto){
			
			resp = new HashMap<String, Object>();
			boolean success = service.updatePassword(dto.getUser_id(),dto.getPass());
			resp.put("success", success);
			
			return resp;
		}
		
}
