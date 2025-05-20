package com.eat.mypage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.subst.Token.Type;

@Service
public class MypageService {

	@Autowired
	MypageDAO dao;
	Logger log = LoggerFactory.getLogger(getClass());
	private final String root = "c:/upload";

	// 회원정보 수정
	public boolean member_update(MypageDTO dto) {
		
		return dao.member_update(dto);
	}
	
	//프로필 사진 변경
	public boolean profile_update(MultipartFile[] files, MypageDTO dto) {
		if(files != null && files.length>0) {
			for (MultipartFile file : files) {
				String fileSaved = fileSave(file);
			
				int newImgIdx = dao.saveProfileImg(fileSaved);
			dto.setImg_idx(newImgIdx);
			}
			
		}
		return dao.profile_update(dto);
	}
	
	//프로필 사진 저장
	private String fileSave(MultipartFile file) {
		
		return null;
	}

	//프로필 사진 저장
	

	

	// 선호 태그 수정
	public boolean member_tag_prefer_update(Map<String, List<String>> params, String user_id) {
		List<String> removeTags = params.get("remove");
		List<String> addTags = params.get("add");

		// 1. 태그 삭제
		if (removeTags != null && !removeTags.isEmpty()) {
			dao.deletMemberTags(user_id, removeTags);
		}

		// 2. 태그 추가
		if (addTags != null && !addTags.isEmpty()) {
			dao.addMemberTags(user_id, addTags);
		}

		return true;
	}


	

}
