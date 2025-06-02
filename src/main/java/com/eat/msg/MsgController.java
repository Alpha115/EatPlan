package com.eat.msg;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.MsgDTO;
import com.eat.utils.JwtUtil;

@CrossOrigin
@RestController
public class MsgController {

	@Autowired
	MsgService service;
	Map<String, Object> resp = null;
	Logger log = LoggerFactory.getLogger(getClass());

	// 쪽지 작성
	@PostMapping(value = "/{user_id}/write_msg")
	public Map<String, Object> write_msg(@PathVariable String user_id, @RequestBody Map<String, String> params,
			@RequestHeader Map<String, String> header) {
		resp = new HashMap<String, Object>();

		String loginId = (String) JwtUtil.readToken(header.get("authorization")).get("user_id");
		if (loginId.equals(user_id)) {
			params.put("user_id", user_id);
			boolean success = service.write_msg(params);
			resp.put("success", success);
		}
		return resp;
	}

	// 받은 쪽지 보기
	@GetMapping(value = "/{user_id}/recip_msg")
	public Map<String, Object> recip_msg(@PathVariable String user_id, @RequestParam(defaultValue = "1") int page,
			@RequestHeader Map<String, String> header) {

		String loginId = (String) JwtUtil.readToken(header.get("authorization")).get("user_id");
		if (loginId.equals(user_id)) {
			resp = service.recip_msg(user_id, page);
		}
		return resp;
	}

	// 보낸 쪽지 조회
	@GetMapping(value = "/{user_id}/send_msg")
	public Map<String, Object> sned_msg(@PathVariable String user_id, @RequestParam(defaultValue = "1") int page,
			@RequestHeader Map<String, String> header) {
		String loginId = (String) JwtUtil.readToken(header.get("authorization")).get("user_id");
		if (loginId.equals(user_id)) {
			resp = service.send_msg(user_id, page);
		}
		return resp;
	}

	// 받은 쪽지 삭제
	@GetMapping(value = "/{user_id}/{msg_idx}/recip_del")
	public Map<String, Object> recip_del(@PathVariable String user_id, @PathVariable int msg_idx, @RequestHeader Map<String, String> header) {
		resp = new HashMap<String, Object>();
		
		String loginId = (String) JwtUtil.readToken(header.get("authorization")).get("user_id");
		if (loginId.equals(user_id)) {
			boolean success = service.recip_del(user_id, msg_idx);
			resp.put("success", success);
		}
		return resp;
	}

	// 보낸 쪽지 삭제
	@GetMapping(value = "/{user_id}/{msg_idx}/send_del")
	public Map<String, Object> send_del(@PathVariable String user_id, @PathVariable int msg_idx, @RequestHeader Map<String, String> header) {

		resp = new HashMap<String, Object>();

		String loginId = (String) JwtUtil.readToken(header.get("authorization")).get("user_id");
		if (loginId.equals(user_id)) {
			/*코드입력*/
			boolean success = service.send_del(user_id, msg_idx);
			resp.put("success", success);
		}
		return resp;
	}

	// 쪽지 상세보기
	@GetMapping(value = "/{user_id}/{msg_idx}/msg_detail")
	public Map<String, Object> msg_detail(@PathVariable String user_id, @PathVariable int msg_idx, @RequestHeader Map<String, String> header) {

		resp = new HashMap<String, Object>();
		String loginId = (String) JwtUtil.readToken(header.get("authorization")).get("user_id");
		if (loginId.equals(user_id)) {
			MsgDTO dto = service.msg_detail(user_id, msg_idx);
			if (dto != null) {
				resp.put("success", true);
				resp.put("message", dto);
			} else {
				resp.put("success", false);
				resp.put("error", "존재하지 않거나 권한이 없는 쪽지입니다.");
			}
		}
		return resp;
	}

}
