package com.eat.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class MypageController {

	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	MypageService service;

	// 회원정보 수정
	@PostMapping(value = "/member_update", consumes = {"multipart/form-data"})
	public Map<String, Object> member_update(@RequestPart("dto") MypageDTO dto,
			@RequestPart(value= "files", required = false) MultipartFile[] files) {

		resp = new HashMap<String, Object>();
		
		for (MultipartFile file : files) {
			log.info("file name : " + file.getOriginalFilename());
		}
		
		
		if(service.nickNameOverlay(dto.getNickname(), dto.getUser_id())) { //--> 닉네임 중복
			resp.put("success", false);
			resp.put("message", "해당 닉네임은 이미 사용 중입니다.");
			return resp;
		}
		
		if(service.emailOverlay(dto.getEmail(), dto.getUser_id())) { //--> 이메일 중복
			resp.put("success", false);
			resp.put("message", "해당 이메일은 이미 사용 중입니다.");
			return resp;
		}
		
		boolean success = service.member_update(dto,files);
		resp.put("success", success);

		return resp;
	}
	
	//태그 수정
	@PostMapping(value="/member_tag_prefer_update")
	public Map<String, Object>member_tag_prefer_update(@RequestBody Map<String, List<Integer>>params,
			@RequestParam String user_id){
		
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean login = false;
		boolean success = service.member_tag_prefer_update(params,user_id);
		resp.put("success", success);
		login = true;
		
		return resp;
	}
	
	
	
	
}
