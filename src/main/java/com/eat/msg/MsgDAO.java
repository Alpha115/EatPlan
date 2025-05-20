package com.eat.msg;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MsgDAO {
	
	//쪽지 보내기
	int write_msg(Map<String, String> params);
	
	//받은 쪽지 보기
	List<MsgDTO> recip_msg(String user_id, int offset, int msg_count);
	
	//보낸 쪽지 보기
	List<MsgDTO> send_msg(String user_id, int offset, int msg_count);
	
	//받은 쪽지 삭제
	int recip_del(String user_id, int msg_idx);
	
	//보낸 쪽지 삭제
	int send_del(String user_id, int msg_idx);
	
	//쪽지 상세보기
	MsgDTO msg_detail(String user_id, int msg_idx);

}
