package com.eat.regist;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.tags.TagCateDTO;
import com.eat.tags.TagDTO;

@Service
public class RegiUtilService {
	@Autowired RegiUtilDAO dao;
	
	public ArrayList<TagCateDTO> listTagCate() {
		return dao.listTagCate();
	}
	
	public ArrayList<TagDTO> listTag(String tagcate_idx) {
		return dao.listTag(tagcate_idx);
	}
	// 지역+식당/코스 태그를 통합해서 검색하는 기능입니다.
	public ArrayList<TagDTO> searchTag(String tag) {
		ArrayList<TagDTO> arr = new ArrayList<TagDTO>();
		arr.addAll(dao.fromTag(tag));
		arr.addAll(dao.fromLocTag(tag));
		return arr;
	}

	// 지역+식당 태그를 통합해서 식당을 검색하는 기능입니다. (미완성)
	public ArrayList<RestaurantDTO> searchResta(Map<String, String> param) {
		
		return null;
	}

	// 지도에 식당 좌표찍기 (위도,경도 가져오기)
	public ArrayList<RestaurantDTO> resta_coor(String resta_idx) {
		
		return dao.resta_coor(resta_idx);
	}

}
