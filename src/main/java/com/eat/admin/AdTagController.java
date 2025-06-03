package com.eat.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagCateDTO;
import com.eat.tags.TagDTO;
import com.eat.tags.TagRequestDTO;

// 식당과 코스 태그를 관리하는 컨트롤러입니다. 모든 기능에는 관리자 권한 토큰이 필요합니다.
@CrossOrigin
@RestController
public class AdTagController {

	@Autowired
	AdTagService service;

	Map<String, Object> resp = null;

	// (관리자 페이지)식당의 리스트를 불러와 페이징처리하는 함수입니다.
	@GetMapping("/adtag_restaList/{page}")
	public Map<String, Object> restaList(@PathVariable String page) {
		resp = new HashMap<String, Object>();

		Map<String, Object> restaList = service.restaList(page);
		resp.put("restaList", restaList);
		return resp;
	}

	// (관리자 페이지) 식당에 식당 태그/지역 태그를 추가합니다.
	// 지역 태그는 FK로, insert가 아닌 update가 이루어져야 합니다.
	@PostMapping("/adtag_addTagResta")
	public Map<String, Object> addTags(@RequestBody Tags tags) {
		resp = new HashMap<String, Object>();
		boolean success = service.addTags(tags);
		resp.put("success", success);
		return resp;
	}

	// 태그 카테고리 추가
	@PostMapping("/adtag_cate")
	public Map<String, Object> adtag_cate(@RequestBody TagCateDTO dto) {

		resp = new HashMap<String, Object>();

		boolean success = service.adtag_cate(dto);

		resp.put("success", success);

		return resp;
	}

	// 태그 카테고리 삭제
	@DeleteMapping("/adtag_cate_del")
	public Map<String, Object> adtag_cate_del(@RequestBody TagCateDTO dto) {

		resp = new HashMap<String, Object>();

		boolean success = service.adtag_cate_del(dto);

		resp.put("success", success);

		return resp;
	}

	// 태그 추가
	@PostMapping("/adtag_write")
	public Map<String, Object> adtag_write(@RequestBody TagRequestDTO req) {

		resp = new HashMap<String, Object>();
		TagCateDTO cate_name = req.getCate_name();
		TagAreaDTO tag_area = req.getTag_area();
		TagDTO tag = req.getTag();

		boolean success = service.adtag_write(cate_name, tag_area, tag);

		resp.put("success", success);

		return resp;
	}
	
//	// 태그중복확인
//	@GetMapping("/adtag_overlay/{tag_name}")
//	public Map<String, Object> adtag_overlay(@PathVariable String tag_name){
//		resp=new HashMap<String, Object>();
//		boolean usable=service.adtag_overlay(tag_name);
//		resp.put("usable", usable);
//		return resp;
//	}

	// 태그 삭제
	@DeleteMapping("/adtag_del")
	public Map<String, Object> adtag_del(@RequestBody Map<String, Integer> params) {

		resp = new HashMap<String, Object>();

		boolean success = service.adtag_del(params);

		resp.put("success", success);

		return resp;
	}

}

class Tags {

	private int[] tag_idx; // 태그 n개
	private int resta_idx; // 식당 1개
	private int area_tag_idx; // 지역태그 1개

	public int[] getTag_idx() {
		return tag_idx;
	}

	public void setTag_idx(int[] tag_idx) {
		this.tag_idx = tag_idx;
	}

	public int getResta_idx() {
		return resta_idx;
	}

	public void setResta_idx(int resta_idx) {
		this.resta_idx = resta_idx;
	}

	public int getArea_tag_idx() {
		return area_tag_idx;
	}

	public void setArea_tag_idx(int area_tag_idx) {
		this.area_tag_idx = area_tag_idx;
	}

}
