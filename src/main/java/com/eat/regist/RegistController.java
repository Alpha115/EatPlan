package com.eat.regist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eat.main.CourseDTO;
import com.eat.main.CourseTagDTO;
import com.eat.main.DetailCmtDTO;
import com.eat.main.DetailRestaDTO;
import com.eat.main.TimelineDTO;

@CrossOrigin
@RestController
public class RegistController {

	@Autowired RegistService service;
	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());
	
	// 코스 작성 (제대로 안돌아감)
	@PostMapping (value="/regist_write")
	public Map<String, Object> regist_write(
			@RequestBody RegistRequestDTO request){
		resp = new HashMap<String, Object>();
		CourseDTO content = request.getContent();
	    List<DetailRestaDTO> content_detail = new ArrayList<DetailRestaDTO>();
	    DetailCmtDTO content_detailcmt = request.getContent_detailcmt();
	    CourseTagDTO tag = request.getTag();
	    TimelineDTO time = request.getTime();
		boolean success = service.regist_write(content, content_detail, content_detailcmt, tag, time);
		resp.put("success", success);
		return resp;
	}
}
