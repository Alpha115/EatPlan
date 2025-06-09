package com.eat.admin;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eat.dto.RestaurantDTO;
import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagCateDTO;
import com.eat.tags.TagDTO;

@Mapper
public interface AdTagDAO {

	ArrayList<RestaurantDTO> restaList(int offset, int limit, String sort);

	int pages(int limit);

	int restaTag(Map<String, Integer> map);
	int addTags(int resta_idx, int[] tag_idx);
	int addAreaTag(int resta_idx, int area_tag_idx);

	// 태그 카테고리 추가, 중복확인, 삭제
	int adtag_cate(TagCateDTO dto); // 태그 카테고리 추가
	int adtag_cate_overlay(TagCateDTO dto); // 태그 카테고리 중복확인
	int adtag_cate_del(TagCateDTO dto); // 태그 카테고리 삭제

	// 태그 추가, 중복확인, 삭제
	int adtag_write_area(TagAreaDTO tag_area); // 지역 태그 추가
	int adtag_write(TagDTO tag); // 태그 추가
	int tag_area_overlay(String tag_area_name); // 지역 태그 중복확인
	int tag_overlay(String tag_name); // 태그 중복확인
	int adtag_del_area(int area_tag_idx); // 지역 태그 삭제
	int adtag_del(int tag_idx); // 태그 삭제

	int adtag_ovaerlay(String tag_name);

	// 식당의 일반 태그를 불러오는 함수
	ArrayList<String> getDefaultTags(String resta_name);

	// 식당의 지역 태그를 불러오는 함수(지역태그의 정보 전부를 불러와야 하면 String이 아니라 DTO로 반환
	String getAreaTag(String resta_name);

	// 대분류 삭제
	int adtag_del_city(String city);

	// 중분류 삭제
	int adtag_del_dist(String dist);	
	
	


}
