package com.eat.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageTagService {

	@Autowired
	ManageTagDAO dao;

	public boolean addTag(DefaultTag tag) {
		int row = dao.addTag(tag.getCate_idx(), tag.getIsClass(), tag.getTag_name());
		return row > 0 ? true : false;
	}

	public boolean overlayTag(String isClass, String tag_name) {
		int count = dao.overlayTag(isClass, tag_name);
		return count == 0 ? true : false;
	}

	public boolean deleteTag(String isClass, String tag_name) {
		int row = dao.deleteTag(isClass, tag_name);
		return row > 0 ? true : false;
	}

}
