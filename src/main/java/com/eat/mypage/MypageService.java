package com.eat.mypage;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eat.dto.CourseDTO;
import com.eat.dto.MypageDTO;
import com.eat.dto.PhotoDTO;
import com.eat.dto.TagPreferDTO;

@Service
public class MypageService {

	@Autowired
	MypageDAO dao;
	Logger log = LoggerFactory.getLogger(getClass());
	private final String root = "c:/upload";
	
	// 회원정보 불러오기
	public List<MypageDTO> member_list(String user_id) {
		return dao.member_list(user_id);
	}
	
	// 태그 불러오기
	public List<TagPreferDTO> member_tag_list(String user_id) {
		return dao.member_tag_list(user_id);
	}

	// 회원정보 수정
	public boolean member_update(MypageDTO dto) {

		return dao.member_update(dto);
	}
	
	// 회원 탈퇴 (soft delete)
	public boolean member_secession(Map<String, String> params) {
		int row = dao.member_secession(params);
		return row > 0 ? true : false;
	}

	// 프로필 사진 변경
	@Transactional
	public boolean profile_update(MypageDTO dto) {
		
		MultipartFile[] files = dto.getFiles();
		
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				String fileSaved = fileSave(file);
				
				PhotoDTO photoDTO = new PhotoDTO();
				photoDTO.setNew_filename(fileSaved);
				photoDTO.setPhoto_class("profile");
				
				dao.saveProfileImg(photoDTO); // 사진 DB에 저장
				int newImgIdx =  photoDTO.getImg_idx();
				
				log.info("newImgidx = " + newImgIdx);
				
				dto.setImg_idx(newImgIdx);
			}

		}
		int row = dao.profile_update(dto);
		return row >0;
	}

	// 프로필 사진 저장
	private String fileSave(MultipartFile file) {

		// 1.확장자 추출해서
		String ori_fileName = file.getOriginalFilename();
		String ext = "";
		if (ori_fileName != null && ori_fileName.contains(".")) {
			ext = ori_fileName.substring(ori_fileName.lastIndexOf("."));
		}

		// 2.새 파일명 만들고
		String new_filename = UUID.randomUUID().toString() + ext;

		// 3.저장 경로 정하고
		String imgDir = "c:/upload/";
		File profPath = new File(imgDir);

		// 4.저장 경로 없으면 만들라고 시키고
		if (!profPath.exists()) {
			profPath.mkdirs();
		}

		// 5.파일 저장 되서
		try {
			file.transferTo(new File(imgDir + new_filename));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		// 6.반환 되게 하기
		return new_filename;
	}

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

	// 프로필 삭제
	public boolean profile_del(int img_idx) {

		// 1.삭제할 파일 목록 조회
		List<Map<String, String>> list = dao.photo_del(Integer.valueOf(img_idx));
		log.info("list : {} ", list);

		// 2. 파일 삭제
		if (list != null && !list.isEmpty()) {
			for (Map<String, String> map : list) {
				String filePath = root + "/" + map.get("new_filename");
				File file = new File(filePath);
				if (file.exists()) {
					file.delete();
				}
			}
		}

		// 3. db에서 삭제
		int row = dao.profile_del(img_idx);

		return row > 0;
	}

	// 프로필 정보 가져오기
	public ResponseEntity<Resource> getPhoto(int img_idx, String string) {

		Resource res = null;
		HttpHeaders headers = new HttpHeaders();

		// 1. img_idx로 new_filename, ori_filename 가져오기
		Map<String, String> imgMap = dao.imgInfo(img_idx);
		log.info("imgMap : " + imgMap);

		// 2. new_filename으로 파일 가져오기
		res = new FileSystemResource(root + "/" + imgMap.get("new_filename"));

		if (!res.exists()) { // 파일 존재 여부 체크
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}

		// 3. photo or download에 따라 header 설정 <- 근데 이거 다운로드 필요한지는 모르겠는데
		// 이렇게 배워서 일단 코드 씀.
		try {
			if (string.equals("photo")) {
				String content_type = Files.probeContentType(Paths.get(root + "/" + imgMap.get("new_filename")));
				headers.add("content-Type", content_type);
			} else {
				headers.add("content-Type", "application/octet-stream");
				String ori_filename = URLEncoder.encode(imgMap.get("ori_filename"), "UTF-8");
				headers.add("content-Disposition", "attachment;filename=\"" + ori_filename + "\"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// resource , header, status
		return new ResponseEntity<Resource>(res, headers, HttpStatus.OK);
	}
	
	
	// 비밀번호 확인
	public boolean member_pass(MypageDTO dto) {
		String passWord= dao.member_pass(dto.getUser_id());
		
		if (passWord == null)
			return false;
		
		return dto.getPass().equals(passWord);
	}

	// 내가 쓴 게시글 모아보기
	public Map<String, Object> my_course_list(String user_id, String page) {
	    Map<String, Object> resp = new HashMap<String, Object>();
	    
	    int totalCount = dao.pages(user_id); // user_id 기준 페이지 수 계산
	    int p = Integer.parseInt(page);
	    int offset = (p - 1) * 10;

	    List<CourseDTO> list = dao.my_course_list(user_id, offset);

	    resp.put("list", list);
	    resp.put("totalCount", totalCount); // 프론트에서 사용할 총 페이지 수 or 총 게시글 수

	    return resp;
	}

	// 내가 좋아요 한 글 모아보기
	public Map<String, Object> like_course_list(String user_id, String page) {
		Map<String, Object> resp = new HashMap<String, Object>();
	    
	    int totalCount = dao.pages(user_id); // user_id 기준 페이지 수 계산
	    int p = Integer.parseInt(page);
	    int offset = (p - 1) * 10;
	    List<CourseDTO> list = dao.like_course_list(user_id, offset);
		
	    resp.put("list", list);
	    resp.put("totalCount", totalCount);
	    
		return resp;
	}

}
