package com.eat.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.CourseDTO;
import com.eat.dto.MainDTO;
import com.eat.dto.RegistRequestDTO;

@CrossOrigin
@RestController
public class MainController {

	Map<String, Object> resp = null;
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	MainService service;

	// 코스 리스트 불러오기
	@GetMapping(value = "/course_list/{page}")
	public Map<String, Object> course_list(@PathVariable int page) {
		resp = new HashMap<String, Object>();
		resp = service.course_list(page);

		return resp;
	}
	
	// 코스 전체 리스트 불러오기 (전체)
	@GetMapping(value = "/course_list_all")
	public Map<String, Object> course_list_all() {
		resp = new HashMap<String, Object>();
		List<MainDTO> list = service.course_list_all();
		resp.put("list", list);
		return resp;
	}

	// 코스 상세보기
	@GetMapping(value = "/courseDetail")
	public Map<String, Object> courseDetail(@RequestParam int post_idx) {

		resp = new HashMap<String, Object>();

		RegistRequestDTO dto = service.courseDetail(post_idx);
		resp.put("detail", dto); // detail로 수정

		return resp;
	}

	// 코스검색
	@GetMapping(value = "/search_course")
	public ResponseEntity<List<CourseDTO>> search_course(
			@RequestParam(value = "subject", required = false) String subject,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "tag", required = false) String tag) {

		List<CourseDTO> entity = null;
		log.info("받아온 제목 : "+subject+" 받아온 아이디 : "+nickname+" 받아온 태그 : "+tag);

		entity = service.search_course(subject, nickname, tag);
		return ResponseEntity.ok(entity);

	}
	
	// --------------------사진 요청 ---------------------//
	@GetMapping("/image/{new_filename}")
	public ResponseEntity<Resource> getImage(@PathVariable String new_filename){
		return service.getFile(new_filename,"photo");
	}

}
