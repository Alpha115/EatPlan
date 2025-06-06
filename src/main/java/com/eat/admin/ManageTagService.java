package com.eat.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eat.dto.RestaurantDTO;
import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagDTO;
import com.eat.tags.TagRestDTO;

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

	
	/* area_tag_idx, city, dist, tag_name, cate_idx(1) */
	
	@Transactional
	public Map<String, Object> addAreaTag(TagAreaDTO params) {
		Map<String, Object> result = new HashMap<String, Object>();
		int row = 0;
		boolean usable = overlayTag(params.getTag_name());
		result.put("usable", usable);
		if (usable) {
			row = dao.addAreaTag(params);
		}
		if (row > 0) {
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		return result;
	}

	// 중복체크
	boolean overlayTag(String isClass, String tag_name) {
		int count = dao.overlayTag(isClass, tag_name);
		return count == 0 ? true : false;
	}

	// 중복체크오버레이
	boolean overlayTag(String tag_name) {
		int count = dao.overlayAreaTag(tag_name);
		return count == 0 ? true : false;
	}

	// 삭제
	public boolean deleteTag(String isClass, String tag_name) {
		int row = dao.deleteTag(isClass, tag_name);
		return row > 0 ? true : false;
	}

	public boolean deleteAreaTag(String tag) {
		int row = dao.deleteAreaTag(tag);
		return row > 0;
	}

	public RestaurantDTO restaDetail(String resta_idx) {
		return dao.restaDetail(resta_idx);
	}

	public ArrayList<TagDTO> restaTags(String resta_idx) {
		return dao.restaTags(resta_idx);
	}

	public boolean delRestaTag(String resta_idx, String tag_idx) {
		int row = dao.deleteRestaTag(resta_idx, tag_idx);
		return row > 0;
	}

	public boolean addTagToResta(TagRestDTO tag_rest) {
		int row = dao.addTagToResta(tag_rest);
		return row > 0;
	}

	

}
