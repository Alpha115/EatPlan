package com.eat.main;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MainService {
	
	@Autowired MainDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//댓글 작성
	public boolean insert(Map<String, String> params) {
		int row = dao.insert(params);
		
		return row>0;
	}

}
