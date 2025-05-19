package com.eat.notice;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeDAO {

	ArrayList<NoticeDTO> list();

}
