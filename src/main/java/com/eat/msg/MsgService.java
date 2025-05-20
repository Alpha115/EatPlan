package com.eat.msg;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgService {
	
	@Autowired
	MsgDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	//쪽지 보내기
	public boolean write_msg(Map<String, String> params) {
		int row = dao.write_msg(params);
		return row > 0;
	}

	//받은 쪽지 보기
	public Map<String, Object> recip_msg(String user_id, int page) {
		
		Map<String, Object> resp = new HashMap<String, Object>();
		
		int offset = (page - 1) * msg_count;
		
		
		List<MsgDTO> recip_msg = dao.recip_msg(user_id, offset,msg_count); 
		
		resp.put("user_id", user_id);
		resp.put("page",page);
		resp.put("recip_msg", recip_msg);
		resp.put("count", recip_msg.size());
		
		return resp;
	}
	
	//보낸 쪽지 보기
	public Map<String, Object> send_msg(String user_id, int page) {
		Map<String, Object> resp = new HashMap<String, Object>();

		int offset = (page - 1) * msg_count;

		List<MsgDTO> send_msg = dao.send_msg(user_id, offset, msg_count);

		resp.put("user_id", user_id);
		resp.put("page", page);
		resp.put("send_msg", send_msg);
		resp.put("count", send_msg.size());

		return resp;
	}

	//받은 쪽지 삭제
	public boolean recip_del(String user_id, int msg_idx) {
		
		int row = dao.recip_del(user_id,msg_idx);
		return row > 0;
	}
	
	//보낸 쪽지 삭제
		public boolean send_del(String user_id, int msg_idx) {
			
			int row = dao.send_del(user_id,msg_idx);
			return row > 0;
		}

	//쪽지 상세보기
		public MsgDTO msg_detail(String user_id, int msg_idx) {
			MsgDTO msg = dao.msg_detail(user_id, msg_idx);
			return msg;
		}
}
