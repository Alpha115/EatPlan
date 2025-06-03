package com.eat.admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageTagDAO {

	int addTag(int cate_idx, String isClass, String tag_name);

	int overlayTag(String isClass, String tag_name);

	int deleteTag(String isClass, String tag_name);

}
