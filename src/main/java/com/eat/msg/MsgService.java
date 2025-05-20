package com.eat.msg;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgService {
	
	@Autowired
	MsgDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	//쪽지 보내기
	public boolean write_msg(Map<String, String> params) {
		int row = dao.write_msg(params);
		return row > 0;
	}

	//받은 쪽지 보기
	@GetMapping(value="/{user_id}/recip_msg")
	public Map<String, Object>recip_msg(@PathVariable String user_id,
			@RequestParam (defaultValue = "1")int page){
		
		Map<String, Object> resp = service.recip_msg(user_id, page);
		
		return resp;
	}
}
