package com.eat.tags;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
	
	@Autowired TagDAO dao;
	
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
