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

	@Autowired MypageDAO dao;
	Logger log = LoggerFactory.getLogger(getClass());
	private final String root = "c:/upload";
	
	//회원정보 수정
	public boolean member_update(MypageDTO dto, MultipartFile[] files) {
		dao.member_update(dto);
		
		if(files == null || files.length == 0) {
			return true;
		}
		
		if(dto.getImg_idx() == 1) {
			return newImgUpdate(dto.getUser_id(),files);
		}else {
		
		return fileSave(dto.getImg_idx(), files);
	
		}
	}
	
	// 새로운 파일 넣고 img_idx 함수 추가
	private boolean newImgUpdate(String user_id, MultipartFile[] files) {
		
		try {
			for(MultipartFile file : files) {
				String ori_filename = file.getOriginalFilename();
				String ext = ori_filename.substring(ori_filename.lastIndexOf("."));
				String new_filename = UUID.randomUUID()+ ext;
			
				byte[] arr = file.getBytes();
				Path path = Paths.get(root + "/" + new_filename);
				Files.write(path, arr);
				
				// 새 이미지를 DB에 넣기
				dao.fileInsert(ori_filename,new_filename);
				
				// 넣은 이미지의 img_idx 가져오기
				int newImg_Idx = dao.getinsertImgidx();
				
				// 회원 테이블에 새 img_idx 저장
				dao.updateMemberImgIdx(user_id, newImg_Idx);
				
			}
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	//프로필 사진 저장
	private boolean fileSave(int img_idx, MultipartFile[] files) {
		boolean success = false;
		
		Path upload = Paths.get(root); //파일 안전하게 저장하려고
		try {
			if (!Files.exists(upload)) {
				Files.createDirectories(upload);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		for(MultipartFile file : files) {
			String ori_filename = file.getOriginalFilename();
			String ext = ori_filename.substring(ori_filename.lastIndexOf("."));
			String new_filename = UUID.randomUUID()+ ext;
			
			try {
				byte[] arr = file.getBytes();
				Path path = upload.resolve(new_filename);
				Files.write(path, arr);
				
				dao.fileUpdate(img_idx,ori_filename,new_filename);
				success = true;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return success;
	}

	// 닉네임 중복체크
	public boolean nickNameOverlay(String nickname, String user_id) {
		int count = dao.nickNameOverlay(nickname, user_id);
		return count > 0;
	}
	
	// 이메일 중복체크
	public boolean emailOverlay(String email, String user_id) {
		int count = dao.emailOverlay(email, user_id);
		return count > 0;
	}
	
	//선호 태그 수정
	public boolean member_tag_prefer_update(Map<String, List<Integer>> params, String user_id) {
		List<Integer> removeTags = params.get("remove");
		List<Integer> addTags = params.get("add");
		
		//1. 태그 삭제
		if(removeTags != null && !removeTags.isEmpty()) {
			dao.deletMemberTags(user_id, removeTags);
		}
		
		//2. 태그 추가
		if(addTags != null && !addTags.isEmpty()) {
			dao.addMemberTags(user_id, addTags);
		}
		
		return true;
	}
	
	//이미지 정보 가져오기
	public ResponseEntity<Resource> getFile(int img_idx, String type) {
		Resource res = null;
		HttpHeaders headers = new HttpHeaders();
		
		Map<String, String> imgMap = dao.imgInfo(img_idx);
		log.info("imgMap : " + imgMap);
		
		 if (imgMap == null || !imgMap.containsKey("new_filename")) {
		        return ResponseEntity.notFound().build();
		    }
		
		 Path filePath = Paths.get(root, imgMap.get("new_filename")).normalize();
		    res = new FileSystemResource(filePath);
		    
		    if(!res.exists()) {
		    	return ResponseEntity.notFound().build();
		    }
		    
		try {
			if(type.equals("photo")) {
				String contentType = Files.probeContentType(filePath);
				headers.add("content_type", contentType != null ? contentType : "application/octet-stream");
			}else {
				headers.add("content-type", "application/octet-stream");
				String ori_filename = URLEncoder.encode(imgMap.get("ori_filename"),"UTF-8");
				headers.add("content-Disposition", "attachment;filename=\""+ori_filename+"\"");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
		return new ResponseEntity<Resource>(res,headers,HttpStatus.OK);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
