package com.eat.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManageTagService {

	@Autowired
	ManageTagDAO dao;

	// add와 overlay 합침
	@Transactional
	public Map<String, Object> addTag(DefaultTag tag) {
		Map<String, Object> result = new HashMap<String, Object>();
		int row = 0;
		boolean usable = overlayTag(tag.getIsClass(), tag.getTag_name());
		result.put("usable", usable);
		if (usable) {
			row = dao.addTag(tag.getCate_idx(), tag.getIsClass(), tag.getTag_name());
		}
		if (row > 0) {
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		return result;
	}

	boolean overlayTag(String isClass, String tag_name) {
		int count = dao.overlayTag(isClass, tag_name);
		return count == 0 ? true : false;
	}

	public boolean deleteTag(String isClass, String tag_name) {
		int row = dao.deleteTag(isClass, tag_name);
		return row > 0 ? true : false;
	}

}
