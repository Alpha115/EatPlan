package com.eat.regist;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eat.tags.TagCateDTO;
import com.eat.tags.TagDTO;

@Mapper
public interface RegiUtilDAO {
	
	ArrayList<TagCateDTO> listTagCate();

	ArrayList<TagDTO> listTag(String tagcate_idx);

	ArrayList<TagDTO> fromTag(String tag);

	ArrayList<TagDTO> fromLocTag(String tag);

	// 지도에 식당 좌표찍기 (위도,경도 가져오기)
	ArrayList<RestaurantDTO> resta_coor(String resta_idx);
}
