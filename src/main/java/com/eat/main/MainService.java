package com.eat.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MainService {
	
	@Autowired MainDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());

}
