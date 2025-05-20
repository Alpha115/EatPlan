package com.eat.msg;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MsgDAO {

  //쪽지 보내기
	int write_msg(Map<String, String> params);

//받은 쪽지 보기
	List<MsgDTO> recip_msg(String user_id, int offset, int msg_count);
}
