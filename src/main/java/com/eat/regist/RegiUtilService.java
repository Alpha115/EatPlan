package com.eat.regist;

import java.util.ArrayList;

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

	public ArrayList<TagDTO> searchTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}
	
	ArrayList<TagDTO> searchFromTag(String tag){
		return dao.fromTag(tag);
	}
}
