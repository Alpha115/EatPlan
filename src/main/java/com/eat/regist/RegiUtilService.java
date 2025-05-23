package com.eat.regist;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.dto.RestaurantDTO;
import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagCateDTO;
import com.eat.tags.TagDTO;

@Service
public class RegiUtilService {
	@Autowired
	RegiUtilDAO dao;

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

	// 지도에 식당 좌표찍기 (위도,경도 가져오기)
	public ArrayList<RestaurantDTO> resta_coor(String resta_idx) {
		return dao.resta_coor(resta_idx);
	}

	// 식당의 이름으로 검색하는 함수입니다,
	public ArrayList<RestaurantDTO> searchRestaName(String resta_name) {
		return dao.searchRestaName(resta_name);
	}

	// 식당을 지역+식당 태그별로 검색하는 함수입니다.
	// 지역태그안했는데 지역은제발 1개만하자...하알겠다
	public ArrayList<RestaurantDTO> searchRestaTag(String[] resta_tags, String resta_area) {
		ArrayList<RestaurantDTO> result
			= dao.searchRestaTag(resta_tags, resta_tags.length, resta_area);
		return result;
	}

	public ArrayList<TagAreaDTO> listTagArea() {
		return dao.listTagArea();
	}

}
