package com.eat.regist;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.eat.tags.TagCateDTO;
import com.eat.tags.TagDTO;

@Mapper
public interface RegiUtilDAO {
	ArrayList<TagCateDTO> listTagCate();

	ArrayList<TagDTO> listTag(String tagcate_idx);

	ArrayList<TagDTO> fromTag(String tag);
}
