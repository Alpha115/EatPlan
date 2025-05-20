package com.eat.admin;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eat.regist.RestaurantDTO;

@Mapper
public interface AdTagDAO {

	ArrayList<RestaurantDTO> restaList(int offset, int limit);

	int pages(int limit);

	int restaTag(Map<String, Integer> map);

}
