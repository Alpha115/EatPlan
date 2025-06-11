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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.MypageDTO;
import com.eat.dto.TagPreferDTO;

@CrossOrigin
@RestController
public class MypageController {

	Map<String, Object> resp = null;
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	MypageService service;
	
	// 회원정보 불러오기
	@PostMapping(value="/member_list")
	public Map<String, Object> member_list(@RequestBody MypageDTO dto) {
		resp = new HashMap<String, Object>();
		List<MypageDTO> list = service.member_list(dto.getUser_id());
		resp.put("list", list);
		return resp;
	}
	
	// 태그 불러오기
//	@PostMapping(value="/member_tag_list")
//	public Map<String, Object> member_tag_list(@RequestBody TagPreferDTO tagdto) {
//		resp = new HashMap<String, Object>();
//		List<TagPreferDTO> taglist = service.member_tag_list(tagdto.getUser_id());
//		resp.put("taglist", taglist);
//		return resp;
//	}
	@PostMapping("/member_tag_list")
    public Map<String, Object> getMemberTagList(@RequestBody TagPreferDTO dto) {
        Map<String, Object> resp = new HashMap<>();

        List<TagPreferDTO> tagIdxList = service.member_tag_idx(dto.getUser_id());
        resp.put("tagList", tagIdxList);

        List<String> tagNames = service.member_tag_name(dto.getUser_id());
        resp.put("tagnames", tagNames);

        return resp;
    }

	// 회원정보 수정
	@PutMapping(value = "/member_update")
	public Map<String, Object> member_update(@RequestBody MypageDTO dto) {
		resp = new HashMap<String, Object>();
		boolean success = service.member_update(dto);
		resp.put("success", success);
		return resp;
	}
	
	// 회원 탈퇴 (soft delete)
	@PutMapping(value="/member_secession")
	public Map<String, Object> member_secession(@RequestBody Map<String, String> params) {
		resp = new HashMap<String, Object>();
		boolean success = service.member_secession(params);
		resp.put("success", success);
		return resp;
	}
	
	//비밀번호 확인 기능
	// ---------------프론트 기능 아닌가
	@PostMapping("/member_pass")
	public Map<String, Object> member_pass(@RequestBody MypageDTO dto){
		resp = new HashMap<String, Object>();
		boolean success = service.member_pass(dto);
		resp.put("success", success);
		return resp;
	}
	
	
	//프로필 변경
	@PutMapping(value="/profile_update")
	public Map<String, Object> profile_update(@ModelAttribute MypageDTO dto){
		resp = new HashMap<String, Object>();
		boolean success =service.profile_update(dto);
		resp.put("success", success);
		return resp;
	}
	
	//프로필 삭제
	@PutMapping(value="/profile_del/{user_id}")
	public Map<String, Object> profile_del (@PathVariable String user_id) {
		resp = new HashMap<String, Object>();
		boolean success = service.profile_del(user_id);
		resp.put("success", success);
		return resp;
	}
	
	//프로필 정보 가져오기
	@GetMapping(value="/photo/{img_idx}")
	public ResponseEntity<Resource> photo(@PathVariable int img_idx){
		log.info("img_idx : " + img_idx);
		return service.getPhoto(img_idx, "photo");
	}
	
	// 태그 수정 (안 쓸 것 같아요)
	@PostMapping(value = "/member_tag_prefer_update")
	public Map<String, Object> member_tag_prefer_update(@RequestBody Map<String, List<String>> params,
			@RequestParam String user_id) {
		resp = new HashMap<String, Object>();
		boolean success = service.member_tag_prefer_update(params, user_id);
		resp.put("success", success);
		return resp;
	}
	
	// 마이페이지 태그 삭제
	@DeleteMapping(value = "/member_tag_prefer_delete")
	public Map<String, Object> member_tag_prefer_delete(@RequestBody MypageDTO dto) {
	    String user_id = dto.getUser_id();
	    boolean success = service.member_tag_prefer_delete(user_id);
	    Map<String, Object> resp = new HashMap<>();
	    resp.put("success", success);
	    return resp;
	}
	
	// 마이페이지 태그 추가
	@PostMapping(value = "/member_tag_prefer_insert")
	public Map<String, Object> member_tag_prefer_insert(@RequestBody Register dto) {
		resp = new HashMap<String, Object>();
		boolean success = service.member_tag_prefer_insert(dto);
		resp.put("success", success);
		return resp;
	}
	
	// 내가 쓴 게시글 모아보기
	@GetMapping(value="/my_course_list/{user_id}/{page}")
	public Map<String, Object> my_course_list(@PathVariable String user_id, @PathVariable String page) {
		resp = new HashMap<String, Object>();
		Map<String, Object> list = service.my_course_list(user_id, page);
		resp.put("list", list);
		return resp;
	}
	
	// 내가 좋아요 한 글 모아보기
	@GetMapping(value="/like_course_list/{user_id}/{page}")
	public Map<String, Object> like_course_list(@PathVariable String user_id, @PathVariable String page) {
		resp = new HashMap<String, Object>();
		Map<String, Object> list = service.like_course_list(user_id, page);
		resp.put("list", list);
		return resp;
	}
	
	// 이메일 중복 체크
	@GetMapping(value = "/overlay/email/{email}")
	public Map<String, Object> overlayEmail(@PathVariable String email) {
		resp = new HashMap<String, Object>();
		boolean success = service.overlayEmail(email);
		resp.put("success", success);
		return resp;
	}
	
	// 마이페이지 비밀번호 변경 (기존 비밀번호, 새 비밀번호)
	@PutMapping(value="/mypage_updatePassword")
	public Map<String, Object> mypage_updatePassword(@RequestBody Map<String, String> params) {
		String user_id = params.get("user_id");
	    String existing_pass = params.get("existing_pass");
	    String new_pass = params.get("new_pass");
	    
	    Map<String, Object> resp = new HashMap<String, Object>();
	    boolean success = service.mypage_updatePassword(user_id, existing_pass, new_pass);
	    resp.put("success", success);
		return resp;
	}
	
}

//멤버와 태그목록을 동시에받는거
class Register {
	
	private TagPreferDTO[] tags; // 3개 배열로 받음


	public TagPreferDTO[] getTags() {
		return tags;
	}

	public void setTags(TagPreferDTO[] tags) {
		this.tags = tags;
	}

	
	
	
	
	
	
}
	