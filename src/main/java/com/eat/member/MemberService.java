package com.eat.member;

import java.io.File;
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
				
				//여기부터
				Map<String, Object> param = new HashMap<>();
				param.put("class", "profile");
				param.put("new_filename", fileSaved);

				int newImgIdx = dao.saveProfileImg(param);
				//여기까지
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

	

}
