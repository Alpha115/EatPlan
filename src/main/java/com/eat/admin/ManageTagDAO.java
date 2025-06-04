package com.eat.admin;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageTagDAO {

	int addTag(int cate_idx, String isClass, String tag_name);
	int addAreaTag(Map<String, String> tag);

	int overlayTag(String isClass, String tag_name);
	int overlayAreaTag(String tag_name);

	int deleteTag(String isClass, String tag_name);
	int deleteAreaTag(String tag);


}
