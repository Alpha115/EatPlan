package com.eat.tags;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagDAO {

	ArrayList<TagCateDTO> listTagCate();

	ArrayList<TagDTO> listTag(String tagcate_idx);

	ArrayList<TagDTO> fromTag(String tag);

}
