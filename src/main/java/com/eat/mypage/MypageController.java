package com.eat.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	@PutMapping(value = "/member_update")
	public Map<String, Object> member_update(@RequestBody MypageDTO dto) {
		
		boolean success = service.member_update(dto);
		resp.put("success", success);

		return resp;
	}
	
	//프로필 변경
	@PutMapping(value="/profile_update")
	public Map<String, Object> profile_update(@RequestParam(required = false) MultipartFile[] files,
			MypageDTO dto){
		
		for(MultipartFile file : files) {
			log.info("file name : " + file.getOriginalFilename());
		}
		
		resp = new HashMap<String, Object>();
		
		if(files != null) {
			for (MultipartFile file : files) {
				log.info("file name : " + file.getOriginalFilename());
			}
		}
		// ↑ 프로필 설정x 기본 회원 정보만 수정 했을 때 완료시키기 위해
		
		boolean success =service.profile_update(files, dto);
		resp.put("success", success);
		resp.put("img_idx", dto.getImg_idx());
		
		return resp;
	}
	
	
	
	// 태그 수정
	@PostMapping(value = "/member_tag_prefer_update")
	public Map<String, Object> member_tag_prefer_update(@RequestBody Map<String, List<String>> params,
			@RequestParam String user_id) {

		resp = new HashMap<String, Object>();
		
		boolean success = service.member_tag_prefer_update(params, user_id);
		resp.put("success", success);
		
		return resp;
	}
}
	