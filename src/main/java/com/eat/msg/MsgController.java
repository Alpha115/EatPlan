package com.eat.msg;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MsgController {
	
	@Autowired
	MsgService service;
	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());

	//쪽지 작성
	@PostMapping(value="/{user_id}/write_msg")
	public Map<String, Object>write_msg(@PathVariable String user_id,
			@RequestBody Map<String, String>params){
		
		log.info("user_id: " + user_id);
        log.info("params : " + params);
        
        resp = new HashMap<String, Object>();
        params.put("user_id", user_id);
        boolean success = service.write_msg(params);
        resp.put("success", success);
        
		return resp;
	}

	//받은 쪽지 보기
	@GetMapping(value="/{user_id}/recip_msg")
	public Map<String, Object>recip_msg(@PathVariable String user_id,
			@RequestParam (defaultValue = "1")int page){
		
		Map<String, Object> resp = service.recip_msg(user_id, page);
		
		return resp;
	}

	//보낸 쪽지 조회
	@GetMapping(value="/{user_id}/send_msg")
	public Map<String, Object>sned_msg(@PathVariable String user_id,
			@RequestParam (defaultValue = "1")int page){
		
		Map<String, Object> resp = service.send_msg(user_id, page);
		
		return resp;
	}

	//받은 쪽지 삭제
	@DeleteMapping(value="/{user_id}/{msg_idx}/recip_del")
	public Map<String, Object>recip_del(@PathVariable String user_id,
			@PathVariable int msg_idx){
		
		Map<String, Object> resp = new HashMap<String, Object>();
		
		boolean success =  service.recip_del(user_id,msg_idx);
		resp.put("success", success);
		
		return resp;
	}
	
	//보낸 쪽지 삭제
		@DeleteMapping(value="/{user_id}/{msg_idx}/send_del")
		public Map<String, Object>send_del(@PathVariable String user_id,
				@PathVariable int msg_idx){
			
			Map<String, Object> resp = new HashMap<String, Object>();
			
			boolean success =  service.send_del(user_id,msg_idx);
			resp.put("success", success);
			
			return resp;
		}


	
}
