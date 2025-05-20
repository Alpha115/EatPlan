package com.eat.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.tags.TagRestDTO;

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

	public boolean restaTag(ArrayList<TagRestDTO> tags) {
		// 받은 태그의 개수만큼 insert 쿼리문을 실행합니다.
		int row = 0;
		for (int i = 0; i < tags.size(); i++) {
			row += dao.restaTag(tags.get(i));
		}
		log.info("AdTagService-restaTag 에서 insert한 row: {} 개", row);
		return row > 0 ? true : false;
	}

}
