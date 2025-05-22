package com.eat.report;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eat.dto.CourseDTO;
import com.eat.dto.MainDTO;
import com.eat.dto.MsgDTO;
import com.eat.dto.MypageDTO;
import com.eat.dto.PhotoDTO;
import com.eat.dto.ReportDTO;
import com.eat.dto.ReportHistoryDTO;

@Service
public class ReportService {
	
	@Autowired ReportDAO dao;
	Map<String, Object> resp = null;
	private int content_cnt = 10;

	// 신고 목록 불러오기
	public List<ReportDTO> report_list(int page) {
		int offset = (page - 1)*content_cnt;
		return dao.report_list(offset, content_cnt);
	}
	
	// 신고 상세보기 몸통
	public ReportDTO report_detail(int report_idx) {
		return dao.report_detail(report_idx);
	}
	// 신고 상세보기 - 신고된 코스 정보
	public CourseDTO report_course(int reported_idx) {
		return dao.report_course(reported_idx);
	}
	// 신고 상세보기 - 신고된 쪽지 정보
	public MsgDTO report_msg(int reported_idx) {
		return dao.report_msg(reported_idx);
	}
	// 신고 상세보기 - 신고된 댓글 정보
	public MainDTO report_cmt(int reported_idx) {
		return dao.report_cmt(reported_idx);
	}
	// 신고 상세보기 - 이미지
	public PhotoDTO photo(int img_idx) {
		return dao.photo(img_idx);
	}
	
	// 히스토리 작성
	public boolean history_write(ReportHistoryDTO content) {
		int row = dao.history_write(content);
		return row>0;
	}

	// 히스토리 불러오기
	public List<ReportHistoryDTO> history_list(int report_idx, int page) {
		int offset = (page - 1) * content_cnt;
		return dao.history_list(report_idx, offset, content_cnt);
	}

	// 신고 목록 총 페이지
	public int report_pages() {
		return dao.report_pages(content_cnt);
	}
	
	// 히스토리 총 페이지
	public int his_pages(int report_idx) {
		return dao.his_pages(content_cnt, report_idx);
	}

	// 신고 글 쓰기
	public boolean report_write(ReportDTO content, MultipartFile[] files) {

		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				String fileSaved = fileSave(file);
				
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("new_filename", fileSaved);
				//param.put("img_idx", content.getImg_idx());
				
				int imgIdx = dao.saveReportImg(param); // 사진 DB에 저장
				content.setImg_idx(imgIdx);
			}
		}
		
		int row = dao.report_write(content);
		
		return row>0;
	}

	// 프로필 사진 저장
	private String fileSave(MultipartFile file) {

		// 1.확장자 추출해서
		String ori_fileName = file.getOriginalFilename();
		String ext = "";
		if (ori_fileName != null && ori_fileName.contains(".")) {
			ext = ori_fileName.substring(ori_fileName.lastIndexOf("."));
		}

		// 2.새 파일명 만들고
		String new_filename = UUID.randomUUID().toString() + ext;

		// 3.저장 경로 정하고
		String imgDir = "c:/upload/";
		File Path = new File(imgDir);

		// 4.저장 경로 없으면 만들라고 시키고
		if (!Path.exists()) {
			Path.mkdirs();
		}

		// 5.파일 저장 되서
		try {
			file.transferTo(new File(imgDir + new_filename));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		// 6.반환 되게 하기
		return new_filename;
	}





}
