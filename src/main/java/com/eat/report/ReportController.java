package com.eat.report;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ReportController {
	
	@Autowired ReportService service;
	
	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());

}
