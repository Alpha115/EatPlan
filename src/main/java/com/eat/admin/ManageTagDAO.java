package com.eat.admin;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eat.dto.RestaurantDTO;
import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagDTO;
import com.eat.tags.TagRestDTO;

@Mapper
public interface ManageTagDAO {

	int addTag(int cate_idx, String isClass, String tag_name);
	int addAreaTag(TagAreaDTO params);

	int overlayTag(String isClass, String tag_name);
	int overlayAreaTag(String tag_name);

	int deleteTag(String isClass, String tag_name);
	int deleteAreaTag(String tag);
	int deleteRestaTag(String resta_idx, String tag_idx);
	
	RestaurantDTO restaDetail(String resta_idx);
	ArrayList<TagDTO> restaTags(String resta_idx);
	
	int addTagToResta(TagRestDTO tag_rest);
	
	

}
