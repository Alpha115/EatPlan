package com.eat.main;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.eat.dto.CourseDTO;
import com.eat.dto.CourseTagDTO;
import com.eat.dto.DetailCmtDTO;
import com.eat.dto.DetailRestaDTO;
import com.eat.dto.LikedDTO;
import com.eat.dto.MainDTO;
import com.eat.dto.MemberDTO;
import com.eat.dto.PhotoDTO;
import com.eat.dto.RegistRequestDTO;
import com.eat.dto.RestaurantDTO;
import com.eat.dto.StarDTO;
import com.eat.dto.TimelineDTO;
import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagDTO;

@Service
public class MainService {

	@Autowired
	MainDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	private int limit = 10, page = 0; // 1페이지당 뜨는 코스 게시물의 갯수입니다.

	// 코스 리스트 불러오기
	public Map<String, Object> course_list(int page, String sort) {

		Map<String, Object> resp = new HashMap<String, Object>();
		this.page = page;
		resp.put("page", this.page);
		int offset = (this.page - 1) * limit;
		
		String orderBy;
	    switch (sort) {
	        case "date_asc":
	            orderBy = "reg_date ASC";
	            break;
	        case "hit_desc":
	            orderBy = "b_hit DESC";
	            break;
	        case "hit_asc":
	            orderBy = "b_hit ASC";
	            break;
	        case "like_cnt_desc":
	            orderBy = "like_cnt DESC";
	            break;
	        case "like_cnt_asc":
	            orderBy = "like_cnt ASC";
	            break;
	        case "star_avg_desc":
	            orderBy = "star_avg DESC";
	            break;
	        case "star_avg_asc":
	            orderBy = "star_avg ASC";
	            break;
	        case "date_desc":
	        default:
	            orderBy = "reg_date DESC";
	            break;
	    }
		
		List<CourseDTO> list = dao.course_list(offset, limit, orderBy);

		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>();

		if (list != null) {
			for (CourseDTO content : list) {
				Map<String, Object> course_data = new HashMap<String, Object>();
				course_data.put("course", content);
				course_data.put("nickname", dao.course_list_nick(content.getUser_id()));
				course_data.put("cmt_cnt", dao.course_list_cmt_cnt(content.getPost_idx()));
				course_data.put("like_cnt", dao.course_list_like_cnt(content.getPost_idx()));
				course_data.put("star_avg", dao.course_list_star_avg(content.getPost_idx()));
				course_data.put("course_tag", dao.course_list_tag(content.getPost_idx()));
				course_data.put("course_tag_area", dao.course_list_tag_area(content.getPost_idx()));
				List<DetailRestaDTO> detail = dao.detail(content.getPost_idx());
				if (detail != null && !detail.isEmpty()) {
					DetailRestaDTO first_detail = detail.get(0);
					course_data.put("course_img", dao.course_list_img(first_detail.getDetail_idx()));
				}
				result_list.add(course_data);
			}
		}
		
		resp.put("list", result_list);
		resp.put("page", this.page);
		resp.put("pages", dao.pages(limit));
		resp.put("totalCount", dao.totalCount());
		return resp;
	}

	// 코스 상세보기
	@Transactional
	public RegistRequestDTO courseDetail(int post_idx, String isClass) {

		List<DetailRestaDTO> restaList = dao.getDetailRestaList(post_idx);

		for (DetailRestaDTO detail : restaList) {
			int rIdx = detail.getResta_idx();
			RestaurantDTO restaInfo = dao.getRestaurantByIdx(rIdx);

			if (restaInfo.getImg_idx() > 0) {
				PhotoDTO photo = dao.getPhotoByImgIdx(restaInfo.getImg_idx());
				restaInfo.setPhoto(photo);
			}

			List<RestaurantDTO> temp = new ArrayList<RestaurantDTO>();
			temp.add(restaInfo);
			detail.setResta(temp);
		}

		dao.setB_hit(post_idx);
		CourseDTO course = dao.getCourseDTO(post_idx);
		if (course == null) {
		    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 post_idx 입니다: " + post_idx);
		}
		MemberDTO nickname = dao.getNickname(course.getUser_id());
		TimelineDTO timeline = dao.getTimelineDTO(post_idx);
		List<DetailCmtDTO> cmtList = dao.getCmtDTOList(post_idx);
		List<CourseTagDTO> tagList = dao.getCourseList(post_idx);

		RegistRequestDTO resp = new RegistRequestDTO();
		
		resp.setContent(course);
		resp.setTime(timeline);
		resp.setNickname(nickname);
		resp.setContent_detail_resta(restaList);
		resp.setContent_detail_cmt(cmtList);
		resp.setTags(tagList);
		
		return resp;

	}

	// 코스검색
	public List<CourseDTO> search_course(String subject, String nickname, List<String> safeTagList) {
		List<CourseDTO> resp = dao.search_course(subject, nickname, safeTagList, safeTagList.size());

		for (CourseDTO course : resp) {
			List<CourseTagDTO> tags = dao.getTags(course.getPost_idx());
			course.setTags(tags);

			List<TagDTO> tagsName = new ArrayList<>();
			List<TagAreaDTO> tagsAreaName = new ArrayList<>();

			for (CourseTagDTO tagInfo : tags) {
				if ("tag".equals(tagInfo.getIsClass())) {
					tagsName.addAll(dao.searchTags(tagInfo.getIdx()));
				} else if ("area_tag".equals(tagInfo.getIsClass())) {
					tagsAreaName.addAll(dao.searchTagsArea(tagInfo.getIdx()));
				}

			}

			course.setTag_name(tagsName);
			course.setTag_name_area(tagsAreaName);

			List<PhotoDTO> photos = dao.getPhotosByPostIdx(course.getPost_idx());
			course.setPhotos(photos);

			String thumb = dao.courseListImg(course.getPost_idx());
			course.setThumbnail(thumb);

		}
		return resp;
	}

	// 코스 전체 리스트 불러오기 (전체)
	public List<MainDTO> course_list_all() {
		return dao.course_list_all();
	}

	// --------------------사진 요청 ---------------------//
	public ResponseEntity<Resource> getFile(String new_filename, String type) {
		Resource res = null;
		HttpHeaders headers = new HttpHeaders();

		String fileName = dao.fileInfo(new_filename);
		res = new FileSystemResource("C:/upload/" + fileName);

		// 3. photo 냐 download 냐 에 따라 Header 를 설정 해 준다.
		try {
			if (type.equals("photo")) {
				String content_type = Files.probeContentType(Paths.get("C:/upload/" + fileName));
				headers.add("Content-Type", content_type);
			} else {
//				headers.add("Content-Type", "application/octet-stream");		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// resource, header,status
		return new ResponseEntity<Resource>(res, headers, HttpStatus.OK);
	}
	
	
	// ---------------- 사진요청오버로드--------------------//
	public ResponseEntity<Resource> getFile(int img_idx){
		String fileName=dao.findFileName(img_idx);
		return getFile(fileName, "photo");
	}
	
	

	// 좋아요 누르기
	public boolean like(LikedDTO params) {
		
		String isClass = params.getIsClass(); // 코스인지 댓글인지		
		Boolean exist;
		int row = 0;
		
		if (isClass.equals("course")) {
			exist = dao.checkPostLike(params);  // 게시글 좋아요 한적 있는지
			if (exist == null ) {
				params.setLiked(true);
				return dao.insertPostLike(params) > 0; // 게시글 새 좋아요 추가
			} else {
				params.setLiked(!exist);
				return dao.updatePostLike(params) > 0; // 게시글 좋아요 상태 변경
			}
		} else if (isClass.equals("comment")) {
			exist = dao.checkCmtLike(params);  // 댓글 좋아요 한적 있는지
			if (exist == null) {
				params.setLiked(true);
				return dao.insertCmtLike(params) > 0; // 댓글 새 좋아요 추가
			} else {
				params.setLiked(!exist);
				return dao.updateCmtLike(params) > 0; // 댓글 좋아요 상태 변경
			}
		}
		return false;
	}
	
	// 좋아요 체크
	public boolean likeCheck(String user_id, int post_idx) {
		Boolean liked = dao.likeCheck(user_id, post_idx);
		
		if (liked == null) {
			liked = false;
		}
		
		return liked;
	}

	// 별점 주기
	public boolean star(StarDTO params) {
		int row = dao.star(params);
		return row > 0;
	}

	// 좋아요 체크 댓글
	public  List<Map<String, Object>> likeCheckCmt(String user_id, List<Integer> cmtIdxList) {
		return dao.likeCheckCmt(user_id, cmtIdxList);
	}

	// 좋아요 높은 순서대로 코스 리스트 불러오기 (주간)
	public Map<String, Object> weekly_best_list() {
		Map<String, Object> resp = new HashMap<String, Object>();
		List<Map<String, Object>> list = dao.weekly_best_list();
		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>();
		if (list != null) {
			for (Map<String, Object> content : list) {
				Map<String, Object> course_data = new HashMap<String, Object>();
				course_data.put("course", content);
				course_data.put("nickname", dao.best_course_list_nick((String) content.get("user_id")));
				course_data.put("cmt_cnt", dao.best_course_list_cmt_cnt((int) content.get("post_idx")));
				course_data.put("like_cnt", dao.best_course_list_like_cnt((int) content.get("post_idx")));
				course_data.put("star_avg", dao.best_course_list_star_avg((int) content.get("post_idx")));
				course_data.put("course_tag", dao.best_course_list_tag((int) content.get("post_idx")));
				course_data.put("course_tag_area", dao.best_course_list_tag_area((int) content.get("post_idx")));
				List<DetailRestaDTO> detail = dao.best_detail((int) content.get("post_idx"));
				if (detail != null && !detail.isEmpty()) {
					DetailRestaDTO first_detail = detail.get(0);
					course_data.put("course_img", dao.best_course_list_img(first_detail.getDetail_idx()));
				}
				result_list.add(course_data);
			}
		}
		resp.put("list", result_list);
		return resp;
	}

	// 좋아요 높은 순서대로 코스 리스트 불러오기 (월간)
	public Map<String, Object> monthly_best_list() {
		Map<String, Object> resp = new HashMap<String, Object>();
		List<Map<String, Object>> list = dao.monthly_best_list();
		List<Map<String, Object>> result_list = new ArrayList<Map<String, Object>>();
		if (list != null) {
			for (Map<String, Object> content : list) {
				Map<String, Object> course_data = new HashMap<String, Object>();
				course_data.put("course", content);
				course_data.put("nickname", dao.best_course_list_nick((String) content.get("user_id")));
				course_data.put("cmt_cnt", dao.best_course_list_cmt_cnt((int) content.get("post_idx")));
				course_data.put("like_cnt", dao.best_course_list_like_cnt((int) content.get("post_idx")));
				course_data.put("star_avg", dao.best_course_list_star_avg((int) content.get("post_idx")));
				course_data.put("course_tag", dao.best_course_list_tag((int) content.get("post_idx")));
				course_data.put("course_tag_area", dao.best_course_list_tag_area((int) content.get("post_idx")));
				List<DetailRestaDTO> detail = dao.best_detail((int) content.get("post_idx"));
				if (detail != null && !detail.isEmpty()) {
					DetailRestaDTO first_detail = detail.get(0);
					course_data.put("course_img", dao.best_course_list_img(first_detail.getDetail_idx()));
				}
				result_list.add(course_data);
			}
		}
		resp.put("list", result_list);
		return resp;
	}






}
