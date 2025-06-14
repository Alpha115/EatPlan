package com.eat.member;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eat.admin.AdminAuth;
import com.eat.dto.MemberDTO;
import com.eat.dto.TagPreferDTO;
import com.eat.utils.JwtUtil;

@CrossOrigin
@RestController
public class MemberController {

	@Autowired
	MemberService service;
	
	@Autowired
	AdminAuth auth;

	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());
	
	
	
	//user_id -> nickname으로 보이게 하는것
		@GetMapping("/member/{user_id}")
		public Map<String, Object> getMemberId(@PathVariable String user_id) {
			
			Map<String, Object> resp = new HashMap<String, Object>();
			MemberDTO member = service.getMemberId(user_id);
			if(member == null) {
				resp.put("success", false);
				resp.put("err", "해당 사용자가 존재하지 않습니다.");
				return resp;
			}
			
			resp.put("user_id", member.getUser_id());
			resp.put("nickname", member.getNickname());
			resp.put("success", true);
			
			return resp;
		}
		
		@GetMapping("/member/byNickname/{nickname}")
		public ResponseEntity<Map<String, Object>> getUserIdByNickname(@PathVariable String nickname) {
		   
		    String userId = service.findUserIdByNickname(nickname); // 실제 닉네임으로 userId 찾기
		    if (userId == null) {
		        return ResponseEntity.notFound().build();
		    }
		    return ResponseEntity.ok(Map.of("user_id", userId));
		}
		
		
	// 로그인
		@PostMapping(value = "/login")
		public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> params) {
		    Map<String, Object> resp = new HashMap<>();
		    boolean success = service.login(params);
		    resp.put("success", success);
		    if (!success) {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		    }

		    String id = params.get("user_id");
		    boolean adminFlag = auth.authorization(id);

		    // ▼ admin 플래그까지 함께 담아서 JWT 생성
		    Map<String,Object> claims = new HashMap<>();
		    claims.put("user_id", id);
		    claims.put("admin", adminFlag);
		    String token = JwtUtil.getToken(claims);

		    resp.put("token", token);
		    resp.put("user_id", id);
		    resp.put("admin", adminFlag);
		    return ResponseEntity.ok(resp);
		}
		
		// 탈퇴 유저 로그인 제어
		@PostMapping(value="/withdraw_check")
		public Map<String, Object> withdraw_check(@RequestBody MemberDTO dto) {
			Map<String, Object> resp = new HashMap<String, Object>();
			boolean success = service.withdraw_check(dto.getUser_id());
			resp.put("success", success);
			return resp;
		}
		

	// 회원가입
	@PostMapping(value = "/join")
	public Map<String, Object> join(@RequestBody Register info) {
		resp = new HashMap<String, Object>();
		boolean success = service.join(info);
		resp.put("success", success);
		return resp;
	}

	// 아이디 중복 체크
	@GetMapping(value = "/overlay/id/{user_id}")
	public Map<String, Object> overlayId(@PathVariable String user_id) {
		resp = new HashMap<String, Object>();
		boolean success = service.overlayId(user_id);
		resp.put("use", success);
		return resp;
	}

	// 닉네임 중복 체크
	@GetMapping(value = "/overlay/nickname/{nickname}")
	public Map<String, Object> overlayNick(@PathVariable String nickname) {
		resp = new HashMap<String, Object>();
		boolean success = service.overlayNick(nickname);
		resp.put("use", success);
		return resp;
	}

	// 회원가입 프로필 사진 추가
	@PostMapping(value = "/profileUpload")
	public Map<String, Object> profileUpload(@RequestParam(required = false) MultipartFile[] files,
			@ModelAttribute MemberDTO dto) { // form데이터 -> 자바 객체에 자동 매핑 되도록 , setter 호출
		resp = new HashMap<String, Object>();
		boolean success = service.profileUpload(files, dto);
		resp.put("success", success);
		resp.put("img_idx", dto.getImg_idx());
		resp.put("message", success ? "업로드 성공" : "업로드 실패");
		return resp;
	}

	// 비밀번호 찾기 요청 - 아이디 / 이메일 확인
	@PostMapping(value = "/findPassword")
	public Map<String, Object> findPassword(@RequestBody MemberDTO dto) {

		resp = new HashMap<String, Object>();
		boolean success = service.findPassword(dto.getUser_id(), dto.getEmail());
		resp.put("success", success);
		return resp;
	}

	// 비밀번호 찾기 요청 - 비밀번호 바꾸기
	@PutMapping(value = "/updatePassword")
	public Map<String, Object> updatePassword(@RequestBody MemberDTO dto) {

		resp = new HashMap<String, Object>();
		boolean success = service.updatePassword(dto.getUser_id(), dto.getPass());
		resp.put("success", success);

		return resp;
	}
	
	// 회원가입 + 이미지 저장 한 번에
	@PostMapping("/joinWithImage")
	public Map<String, Object> joinWithImage(
	    @RequestPart(value = "files", required = false) MultipartFile[] files,
	    @RequestPart("dto") MemberDTO dto,
	    @RequestPart("tags") TagPreferDTO[] tags
	) {
	    resp = new HashMap<>();
	    boolean success = service.joinWithImage(dto, tags, files);
	    resp.put("success", success);
	    return resp;
	}
	
	// 사진 삭제 요청
	@DeleteMapping(value="/profileImage_delete")
	public Map<String, Object> profileImage_delete(@RequestBody Map<String, Object> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		int img_idx = (int) params.get("img_idx");
		boolean success = service.profileImage_delete(img_idx);
		resp.put("success", success);
		return resp;
	}

}

	


// 멤버와 태그목록을 동시에받는거
class Register {
	
	  
	/*
	 * POST http:// localhost/join Content-Type: application/json
	 * 
	 * { "dto": { "user_id": "user07", "pass": "pass02", "email":
	 * "email02@email.com", "nickname": "유저02", "bio": null, "location": "서울" },
	 * "tags": [ { "idx": 1, "isClass": "resta", "user_id": "user07" }, { "idx": 2,
	 * "isClass": "resta", "user_id": "user07" }, { "idx": 2, "isClass": "resta",
	 * "user_id": "user07" } ]
	 * 
	 * }
	 */

	private MemberDTO dto;
	private TagPreferDTO[] tags; // 3개 배열로 받음

	public MemberDTO getDto() {
		return dto;
	}

	public void setDto(MemberDTO dto) {
		this.dto = dto;
	}

	public TagPreferDTO[] getTags() {
		return tags;
	}

	public void setTags(TagPreferDTO[] tags) {
		this.tags = tags;
	}

	
	
	
	
	
	
}
