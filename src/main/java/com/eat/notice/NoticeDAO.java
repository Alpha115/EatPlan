package com.eat.notice;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeDAO {

	ArrayList<NoticeDTO> list(int offset, int limit);

	int write(Map<String, Object> params);

	NoticeDTO detail(String notice_idx);

	int upHit(String idx);

	int update(NoticeDTO params);

	int delete(String notice_idx);

	int pages(int limit);

}
