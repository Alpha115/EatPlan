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
	public boolean comment_insert(Map<String, String> params) {
		int row = dao.comment_insert(params);
		
		return row>0;
	}
	
	//댓글 수정
	public boolean comment_update(Map<String, String> params) {
		int row = dao.comment_update(params);
		return row>0;
	}

}
