package com.eat.regist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eat.tags.TagCateDTO;
import com.eat.tags.TagDTO;

@CrossOrigin
@RestController
public class RegiUtilController {

	@Autowired
	RegiUtilService service;
	Map<String, Object> resp = null;

	// #1 tag_cate의 리스트 전체를 불러오는 기능입니다.
	@GetMapping("/list_tagcate")
	public Map<String, Object> listTagCate() {
		resp = new HashMap<String, Object>();
		ArrayList<TagCateDTO> list = service.listTagCate();
		resp.put("list_tagcate", list);
		return resp;
	}

	// #2 tag_cate > tag 의 리스트 전체를 불러오는 기능입니다.
	@GetMapping("/list_tag/{tagcate_idx}")
	public Map<String, Object> listTag(@PathVariable String tagcate_idx) {
		resp = new HashMap<String, Object>();
		ArrayList<TagDTO> list = service.listTag(tagcate_idx);
		resp.put("list_tag", list);
		return resp;
	}

	// tag를 검색하는 기능입니다.
	// 아직 지역태그 검색기능 없음
	@GetMapping("search_tag/{tag}")
	public Map<String, Object> searchTag(@PathVariable String tag) {
		resp = new HashMap<String, Object>();
		ArrayList<TagDTO> list = service.searchTag(tag);
		resp.put("result", list);
		return resp;
	}
}
