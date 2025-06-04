package com.eat.admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

	int authorization(String user_id);

	int changePw(int tmpPw, String user_id);

}
