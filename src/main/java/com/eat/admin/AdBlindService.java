package com.eat.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdBlindService {
	
	@Autowired
	AdBlindDAO dao;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
}
