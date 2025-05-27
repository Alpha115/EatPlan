package com.eat.regist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.RestaurantDTO;
import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagCateDTO;
import com.eat.tags.TagDTO;

@CrossOrigin
@RestController
public class RegiUtilController {

	@Autowired
	RegiUtilService service;
	Map<String, Object> resp = null;

	
	// ------------------------------ 태그 불러오기 / 검색------------------------------
	// 태그_카테고리 table 의 리스트 전체를 불러오는 기능입니다.
	@GetMapping("/list_tagcate")
	public Map<String, Object> listTagCate() {
		resp = new HashMap<String, Object>();
		ArrayList<TagCateDTO> list = service.listTagCate();
		resp.put("list_tagcate", list);
		return resp;
	}

	// 특정 태그 카테고리의 리스트 전체를 불러오는 기능입니다.
	@GetMapping("/list_tag/{tagcate_idx}")
	public Map<String, Object> listTag(@PathVariable String tagcate_idx) {
		resp = new HashMap<String, Object>();
		ArrayList<TagDTO> list = service.listTag(tagcate_idx);
		resp.put("list_tag", list);
		return resp;
	}
	
	// 태그 리스트 전체 불러오기
	@GetMapping("/list_tag/")
	public Map<String, Object> listTagWhole() {
		resp = new HashMap<String, Object>();
		ArrayList<TagDTO> list = service.listTagWhole();
		resp.put("list_tag_whole", list);
		return resp;
	}
	
	// 지역 태그 카테고리의 리스트 전체를 불러오는 기능입니다.
	@GetMapping("/list_tag_area")
	public Map<String, Object> listTagArea(){
		resp=new HashMap<String, Object>();
		ArrayList<TagAreaDTO> list=service.listTagArea();
		resp.put("list_area", list);
		return resp;
	}
	

	// 식당/코스/지역 태그를 검색하는 기능입니다.
	@PostMapping("/search_tag")
	public Map<String, Object> searchTag(@RequestBody Map<String, String> param) {
		resp = new HashMap<String, Object>();
		ArrayList<TagDTO> list = service.searchTag(param.get("tag"));
		resp.put("result", list);
		return resp;
	}

	// ------------------------------ 식당 불러오기 / 검색------------------------------
	
	//식당 이름(String)을 검색합니다.
	@PostMapping("search_resta")
	public Map<String, Object> searchRestaName(@RequestBody String resta_name){
		resp=new HashMap<String, Object>();
		ArrayList<RestaurantDTO> result=service.searchRestaName(resta_name);
		resp.put("result", result);
		return resp;
	}
	
	//식당을 태그별로 검색하여 불러옵니다.
	@PostMapping("list_resta")
	public Map<String, Object> searchRestaTag(@RequestBody RestaTags tags){
		resp=new HashMap<String, Object>();
		ArrayList<RestaurantDTO> result=service.searchRestaTag(tags.getResta_tags(), tags.getResta_area());
		resp.put("result", result);
		return resp;
	}
	
	
	
	
	// ------------------------------ 식당 좌표------------------------------
	// 지도에 식당 좌표찍기 (위도,경도 가져오기)
	@GetMapping(value="/resta_coor")
	public Map<String, Object> resta_coor(
			@RequestBody Map<String, String> param){
		
		resp = new HashMap<String, Object>();
		ArrayList<RestaurantDTO> resta_coor = service.resta_coor(param.get("resta_idx"));
		resp.put("coordinates", resta_coor);
		
		return resp;
	}
	
}



// 받아오는 태그를 묶는 클래스
class RestaTags{
	
//	### 요청
//	POST http://localhost/list_resta
//	Content-Type: application/json
//
//	{
//	  "resta_tags":["4인", "데이트", "회식"],
//	  "resta_area": "종로"
//	}

	
	private String resta_area;
	private String[] resta_tags;
	
	public String getResta_area() {
		return resta_area;
	}
	public void setResta_area(String resta_area) {
		this.resta_area = resta_area;
	}
	public String[] getResta_tags() {
		return resta_tags;
	}
	public void setResta_tags(String[] resta_tags) {
		this.resta_tags = resta_tags;
	}
	
	
}
