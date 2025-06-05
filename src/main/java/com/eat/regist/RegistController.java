package com.eat.regist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.CourseDTO;
import com.eat.dto.CourseTagDTO;
import com.eat.dto.DetailCmtDTO;
import com.eat.dto.DetailRestaDTO;
import com.eat.dto.RegistRequestDTO;
import com.eat.dto.TimelineDTO;

@CrossOrigin
@RestController
public class RegistController {

	@Autowired RegistService service;
	Map<String, Object> resp = null;
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
	
	  // 코스 수정 할 게시글 불러오기
	  @GetMapping(value="/regist_update_content")
	  public Map<String, Object> regist_update_content(
			  @RequestParam int post_idx){
	  
	  resp = new HashMap<String, Object>();
	  
	  RegistRequestDTO content = service.regist_update_content(post_idx);
	  
	  resp.put("content", content);
	  
	  return resp;
	  }
	  
	// 코스 수정
	@PutMapping(value="/update/{post_idx}")
	public Map<String, Object> course_update(
			@PathVariable String post_idx,
			@RequestBody RegistRequestDTO req){
		
		resp = new HashMap<String, Object>();
		
		CourseDTO content = req.getContent();
	    List<DetailRestaDTO> content_detail_resta  = req.getContent_detail_resta();
	    List<DetailRestaDTO> content_detail_resta_del  = req.getContent_detail_resta();
	    List<DetailCmtDTO> content_detail_cmt = req.getContent_detail_cmt();
	    List<DetailCmtDTO> content_detail_cmt_del = req.getContent_detail_cmt();
	    List<CourseTagDTO> tags = req.getTags();
	    List<CourseTagDTO> tags_del = req.getTags();
	    TimelineDTO time = req.getTime();
	    log.info("삭제할 태그: " + tags_del.stream()
	    .map(tag -> String.format("isClass=%s, idx=%d", tag.getIsClass(), tag.getIdx()))
	    .collect(Collectors.joining(" | ")));
		boolean success = service.update(
				content,
				content_detail_resta,
				content_detail_resta_del,
				content_detail_cmt,
				content_detail_cmt_del,
				tags, tags_del, time,
				Integer.parseInt(post_idx));
		resp.put("success", success);
		
		return resp;
	}
	
	// 코스 삭제
	@DeleteMapping (value="/delete")
	public Map<String, Object> delete(
			@RequestBody List<CourseDTO> del_idx){
		
		resp = new HashMap<String, Object>();
		boolean success = service.delete(del_idx);
		resp.put("success", success);
		return resp;
	}
	
	
}
