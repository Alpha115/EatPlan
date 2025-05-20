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

}
