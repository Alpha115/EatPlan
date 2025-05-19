package com.eat.notice;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

	@Autowired NoticeDAO dao;
	Logger log = LoggerFactory.getLogger(getClass());

	public ArrayList<NoticeDTO> list() {
		log.info("Notice/Service에서 list를 호출하였습니다.");
		return dao.list();
	}

}
