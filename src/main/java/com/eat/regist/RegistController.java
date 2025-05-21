package com.eat.regist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// 코스 작성
	@PostMapping (value="/regist_write")
	public Map<String, Object> regist_write(@RequestBody RegistRequestDTO req){
		
		resp = new HashMap<String, Object>();
		
		CourseDTO content = req.getContent();
	    List<DetailRestaDTO> content_detail_resta  = req.getContent_detail_resta();
	    List<DetailCmtDTO> content_detail_cmt = req.getContent_detail_cmt();
	    List<CourseTagDTO> tags = req.getTags();
	    TimelineDTO time = req.getTime();
	    
		boolean success = service.regist_write(content, content_detail_resta, content_detail_cmt, tags, time);
		resp.put("success", success);
		return resp;
	}
	
	// 코스 작성 임시저장 불러오기
	@GetMapping(value="/regist_tmp_list/{user_id}/{page}")
	public Map<String, Object> regist_tmp_list(
			@PathVariable String page,
			@PathVariable String user_id){
		
		resp = new HashMap<String, Object>();
		resp = service.regist_tmp_list(user_id, Integer.parseInt(page));
		
		return resp;
	}
	
	// 코스 수정
	@PutMapping(value="/regist_update")
	public Map<String, Object> regist_update(){
		
		resp = new HashMap<String, Object>();
		
		return resp;
	}
	
}
