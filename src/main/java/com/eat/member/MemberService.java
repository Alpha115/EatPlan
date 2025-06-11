package com.eat.member;

import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eat.dto.MemberDTO;
import com.eat.dto.TagPreferDTO;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;

	Logger log = LoggerFactory.getLogger(getClass());

	//user_id -> nickname으로 보이게 하는것
	public MemberDTO getMemberId(String user_id) {
		return dao.getMemberId(user_id);
	}
	
	// 로그인
	public boolean login(Map<String, String> params) {
		int cnt = dao.login(params);
		return cnt > 0 ? true : false;
	}

	// 회원가입
	@Transactional
	public boolean join(Register info) {
		int row = dao.join(info.getDto());
		row+=dao.joinTag(info.getTags());
		return row > info.getTags().length ? true : false;
	}
	// 아이디 중복 체크
	public boolean overlayId(String user_id) {
		int cnt = dao.overlayId(user_id);
		return cnt == 0 ? true : false;
	}
	// 닉네임 중복 체크
	public boolean overlayNick(String nickname) {
		int cnt = dao.overlayNick(nickname);
		return cnt == 0 ? true : false;
	}
	// 비밀번호 찾기 요청 - 아이디 / 이메일 확인
	public boolean findPassword(String user_id, String email) {
		return dao.findPassword(user_id,email) > 0;
	}
	
	//비밀번호 찾기 요청 - 비밀번호 바꾸기
	public boolean updatePassword(String pass, String user_id) {
		int row = dao.updatePassword(user_id,pass);
		return row > 0;
	}
	
	//프로필 업로드
	@Transactional
	public boolean profileUpload(MultipartFile files[], MemberDTO dto) {
		int row = dao.profileUpload(dto);
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				String fileSaved = fileSave(file);
				if(fileSaved == null) return false;
				Map<String, Object> param = new HashMap<>();
				param.put("class", "profile");
				param.put("new_filename", fileSaved);
				int newImgIdx = dao.saveProfileImg(param);
				dto.setImg_idx(newImgIdx);
			}
		}
		return row > 0;
	}

	private String fileSave(MultipartFile file) {
	    String ori_fileName = file.getOriginalFilename();
	    String ext = "";
	    if (ori_fileName != null && ori_fileName.contains(".")) {
	        ext = ori_fileName.substring(ori_fileName.lastIndexOf("."));
	    }

	    String new_fileName = UUID.randomUUID().toString() + ext;
	    String imgDir = "c:/upload/";
	    File profilePath = new File(imgDir);

	    if (!profilePath.exists()) {
	        profilePath.mkdirs(); // 폴더가 없으면 생성
	    }

	    try {
	        // 파일 저장
	        File targetFile = new File(profilePath, new_fileName);
	        file.transferTo(targetFile); // 실제 파일 저장
	        return new_fileName;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public boolean joinWithImage(MemberDTO dto, TagPreferDTO[] tags, MultipartFile[] files) {
		// 1. 이미지 먼저 저장 (img_idx 확보)
	    if (files != null && files.length > 0) {
	        for (MultipartFile file : files) {
	            String savedFile = fileSave(file);
	            if (savedFile == null) return false;

	            Map<String, Object> param = new HashMap<>();
	            param.put("class", "profile");
	            param.put("new_filename", savedFile);
	            dao.saveProfileImg(param);
	            
	            Object rawImgIdx = param.get("img_idx");
	            log.info(">>>> img_idx value: {}", rawImgIdx);
	            log.info(">>>> img_idx type: {}", rawImgIdx.getClass().getName());

	            if (rawImgIdx instanceof BigInteger) {
	                dto.setImg_idx(((BigInteger) rawImgIdx).intValue());
	            } else if (rawImgIdx instanceof Integer) {
	                dto.setImg_idx((Integer) rawImgIdx);
	            } else if (rawImgIdx instanceof Long) {
	                dto.setImg_idx(((Long) rawImgIdx).intValue());
	            } else {
	                throw new IllegalArgumentException("지원되지 않는 img_idx 타입: " + rawImgIdx.getClass());
	            }
	            
//	            Integer imgIdx = (Integer) param.get("img_idx");
//	            if (imgIdx == null) return false;
//
//	            dto.setImg_idx(imgIdx);
	        }
	    } else {
	    	dto.setImg_idx(null);
	    }

	    // 2. 회원 저장 (img_idx 포함)
	    int row = dao.join(dto);

	    // 3. 태그 저장
	    row += dao.joinTag(tags);

	    return row > tags.length;
	}

	public String findUserIdByNickname(String nickname) {
		return dao.findUserIdByNickname(nickname);
	}
	
	// 탈퇴 유저 로그인 제어
	public boolean withdraw_check(String user_id) {
		int count = dao.withdraw_check(user_id);
		return count > 0 ? true : false;
	}

	// 사진 삭제 요청
	public boolean profileImage_delete(int img_idx) {
		int count = dao.profileImage_delete(img_idx);
		return count > 0 ? true : false;
	}

	

	

}
