package com.eat.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.CourseDTO;
import com.eat.dto.MainDTO;
import com.eat.dto.MsgDTO;
import com.eat.dto.PhotoDTO;
import com.eat.dto.ReportDTO;
import com.eat.dto.ReportHistoryDTO;
import com.eat.utils.JwtUtil;

@CrossOrigin
@RestController
public class ReportController {
	
	@Autowired ReportService service;
	Map<String, Object> resp = null;
	Logger log = LoggerFactory.getLogger(getClass());

	// 신고글 작성
	@PostMapping(value="/report_write")
	public Map<String, Object> report_write(@ModelAttribute ReportDTO content) {
	    resp = new HashMap<>();

	    if (content.getSuspect_id() != null && !content.getSuspect_id().isEmpty()) {
	        String suspectUserId = service.getUserIdByNickname(content.getSuspect_id());
	        if (suspectUserId != null) {
	            // suspect_id는 닉네임이므로 → 진짜 id로 변환하고 원래 닉네임도 저장
	            content.setSuspect_nickname(content.getSuspect_id());
	            content.setSuspect_id(suspectUserId);
	        }
	    }

	    // 이건 if문 밖에 있어야 무조건 저장 로직 실행됨
	    boolean success = service.report_write(content);
	    resp.put("success", success);

	    return resp;
	}
	
	// 신고 목록 불러오기
	@GetMapping(value="/report_list/{page}")
	public Map<String, Object> report_list(
			@PathVariable int page){
		
		resp = new HashMap<String, Object>();
		
		List<ReportDTO> list = service.report_list(page);
		int pages = service.report_pages();
		
		resp.put("list", list);
		resp.put("pages", pages);
		
		return resp;
	}
	
	// 신고 상세보기
	@GetMapping(value="/report_detail/{report_idx}")
	public ResponseEntity<Map<String,Object>> report_detail(
			@PathVariable int report_idx,
			@RequestHeader(value = "Authorization", required = false) String authHeader){
		
		resp = new HashMap<String, Object>();
		
		// 1) JWT 파싱
        String loginId = null;
        boolean isAdmin = false;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Map<String,Object> claims = JwtUtil.readToken(token);
                loginId = (String) claims.get("user_id");
                Object rawAdmin = claims.get("admin");
                if (rawAdmin instanceof Boolean) {
                    isAdmin = (Boolean) rawAdmin;
                } else if (rawAdmin instanceof Number) {
                    isAdmin = ((Number) rawAdmin).intValue() == 1;
                } else if (rawAdmin instanceof String) {
                    isAdmin = Boolean.parseBoolean((String) rawAdmin);
                }
                log.info("JWT parsed → user_id: {}, admin: {}", loginId, isAdmin);
            } catch (Exception e) {
                resp.put("error", "유효하지 않은 토큰입니다.");
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(resp);
            }
        }

        // 2) 신고글 조회
        ReportDTO detail = service.report_detail(report_idx);
        if (detail == null) {
            resp.put("error", "해당 신고가 존재하지 않습니다.");
            return ResponseEntity
                    .badRequest()
                    .body(resp);
        }

        // 3) 필수 필드 확인
        if (detail.getIsClass() == null || detail.getReported_idx() == 0) {
            resp.put("error", "신고 정보가 올바르지 않습니다.");
            return ResponseEntity
                    .badRequest()
                    .body(resp);
        }

        // 4) 비공개 글 접근 제어
        if (!detail.isPublic()) {
            boolean isWriter = loginId != null &&
                    loginId.equals(detail.getReporter_id());
            if (!isWriter && !isAdmin) {
                resp.put("error", "권한이 없습니다.");
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(resp);
            }
        }

        // 5) 분류별 콘텐츠 조회
        switch (detail.getIsClass()) {
            case "course":
                resp.put("reported_course", service.report_course(detail.getReported_idx()));
                break;
            case "message":
                resp.put("reported_msg", service.report_msg(detail.getReported_idx()));
                break;
            case "comment":
                resp.put("reported_cmt", service.report_cmt(detail.getReported_idx()));
                break;
            default:
                resp.put("error", "지원되지 않는 신고 유형입니다.");
                return ResponseEntity
                        .badRequest()
                        .body(resp);
        }

        // 6) 이미지 첨부
        Integer imgIdx = detail.getImg_idx();
        if (imgIdx != null && imgIdx > 0) {
            resp.put("photo", service.photo(imgIdx));
        } else {
            resp.put("photo", "no_image");
        }

        // 7) 최종 응답
        resp.put("detail", detail);
        return ResponseEntity.ok(resp);
    }

	
	// 히스토리 작성
	@PostMapping(value="/history_write")
	public Map<String, Object> history_write(
			@RequestBody ReportHistoryDTO content){
		
		resp = new HashMap<String, Object>();
		
		boolean success = service.history_write(content);
		
		resp.put("success", success);
		
		return resp;
	}
	
	// 히스토리 불러오기
	@GetMapping(value="/history_list/{report_idx}")
	public Map<String, Object> history_list(
			@PathVariable int report_idx,
			@RequestParam (defaultValue = "1") int page){
		
		resp = new HashMap<String, Object>();
		
		List<ReportHistoryDTO> list = new ArrayList<ReportHistoryDTO>();
		list = service.history_list(report_idx, page);
		int cnt = list.size();
		int pages = service.his_pages(report_idx);
		
		resp.put("list", list);
		resp.put("cnt", cnt);
		resp.put("pages", pages);
		return resp;
	}
	
	// 신고 처리상태 변경
	@PutMapping(value="/report_done")
	public Map<String, Object> report_done(
			@RequestBody Map<String, String> params){
		
		resp = new HashMap<String, Object>();
		
		boolean success = service.report_done(params.get("report_idx"), params.get("done"));
		
		resp.put("success", success);
		
		return resp;
	}
}
