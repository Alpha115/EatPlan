package com.eat.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagCateDTO;
import com.eat.tags.TagDTO;

@Service
public class AdTagService {

	@Autowired
	AdTagDAO dao;
	Logger log = LoggerFactory.getLogger(getClass());
	private int limit = 8, page = 0; // 1페이지당 뜨는 식당의 갯수입니다.

	public Map<String, Object> restaList(String page) {
		Map<String, Object> result = new HashMap<String, Object>();

		// 페이징 처리를 위한 코드입니다.
		// result map에는 "page":페이지 / "list" / "pages" :total page가 들어갑니다.
		this.page = Integer.parseInt(page);
		result.put("page", this.page);
		int offset = (this.page - 1) * limit;
		result.put("list", dao.restaList(offset, limit));
		result.put("pages", dao.pages(limit));
		return result;
	}

	public boolean restaTag(ArrayList<Map<String, Integer>> tags) {
		// 받은 태그의 개수만큼 insert 쿼리문을 실행합니다.
		int row = 0;
		for (int i = 0; i < tags.size(); i++) {
			row += dao.restaTag(tags.get(i));
		}
		log.info("AdTagService-restaTag 에서 insert한 row: {} 개", row);
		return row > 0 ? true : false;
	}

	// 태그 카테고리 추가, 중복확인
	public boolean adtag_cate(TagCateDTO dto) {

		int row = 0;
		int overlay = dao.adtag_cate_overlay(dto);

		if (overlay == 0) {
			row = dao.adtag_cate(dto);
		}

		return row > 0;
	}

	// 태그 카테고리 삭제
	public boolean adtag_cate_del(TagCateDTO dto) {

		int row = dao.adtag_cate_del(dto);

		return row > 0;
	}

	// 태그 추가
	public boolean adtag_write(TagCateDTO cate_name, TagAreaDTO tag_area, TagDTO tag) {

		String cate = cate_name.getCate_name();
		int cate_idx = cate_name.getCate_idx();
		String tag_area_name = tag_area.getTag_name();
		String tag_name = tag.getTag_name();
		int row = 0;

		if (cate != null) {
			if (cate.equals("지역")) {
				int area_overlay = dao.tag_area_overlay(tag_area_name);
				if (area_overlay == 0) {
					tag_area.setCate_idx(cate_idx);
					row = dao.adtag_write_area(tag_area);
				}
			}else {
				int tag_overlay = dao.tag_overlay(tag_name);
				if (tag_overlay == 0) {
					tag.setCate_idx(cate_idx);
					row = dao.adtag_write(tag);
				}
			}
		}

		return row > 0;
	}

	public boolean adtag_del(TagCateDTO cate_name, TagAreaDTO tag_area, TagDTO tag) {
		
		int row = 0;
		String cate = cate_name.getCate_name();
		
		if (cate != null) {
			if (cate.equals("지역")) {
				row = dao.adtag_del_area(tag_area);
			}else {
				row = dao.adtag_del(tag);
			}
		}
		
		return row > 0;
	}

}
