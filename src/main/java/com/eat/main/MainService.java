package com.eat.main;

import java.net.URLEncoder;
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

import com.eat.dto.CourseDTO;
import com.eat.dto.CourseTagDTO;
import com.eat.dto.DetailCmtDTO;
import com.eat.dto.DetailRestaDTO;
import com.eat.dto.MainDTO;
import com.eat.dto.MemberDTO;
import com.eat.dto.PhotoDTO;
import com.eat.dto.RegistRequestDTO;
import com.eat.dto.RestaurantDTO;
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
	public Map<String, Object> course_list(int page) {

		Map<String, Object> resp = new HashMap<String, Object>();
		this.page = page;
		resp.put("page", this.page);
		int offset = (this.page - 1) * limit;
		List<CourseDTO> list = dao.course_list(offset, limit);

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
		return resp;
	}

	// 코스 상세보기
	public RegistRequestDTO courseDetail(int post_idx) {

		
		List<DetailRestaDTO> restaList = dao.getDetailRestaList(post_idx);
		
		for (DetailRestaDTO detail : restaList) {
			int rIdx = detail.getResta_idx();
			RestaurantDTO restaInfo = dao.getRestaurantByIdx(rIdx);
			
			if (restaInfo.getImg_idx()>0) {
				PhotoDTO photo = dao.getPhotoByImgIdx(restaInfo.getImg_idx());
				restaInfo.setPhoto(photo);
			}
			
			
			List<RestaurantDTO> temp = new ArrayList<RestaurantDTO>();
			temp.add(restaInfo);
			detail.setResta(temp);
		}
		
		
		
		
		
		CourseDTO course = dao.getCourseDTO(post_idx);
		MemberDTO nickname = dao.getNickname(course.getUser_id());
		TimelineDTO timeline = dao.getTimelineDTO(post_idx);
		List<DetailCmtDTO> cmtList = dao.getCmtDTOList(post_idx);
		List<CourseTagDTO> tagList = dao.getCourseList(post_idx);
		List<TagDTO> tagListResult = new ArrayList<TagDTO>();
		List<TagAreaDTO> tagAreaListResult = new ArrayList<TagAreaDTO>();

		RegistRequestDTO resp = new RegistRequestDTO();

		if (tagList != null && tagList.size() > 0) {
			for (CourseTagDTO tag_info : tagList) {
//				String isClass = tag_info.getIsClass();

				List<TagDTO> tags = dao.course_tags(tag_info.getIdx());
				if (tags != null) {
					tagListResult.addAll(tags);
				}

				List<TagAreaDTO> areaTags = dao.course_list_tags_area(tag_info.getIdx());
				if (areaTags != null) {
					tagAreaListResult.addAll(areaTags);
				}
			}
		}
				resp.setContent(course);
				resp.setTime(timeline);
				resp.setNickname(nickname);
				resp.setContent_detail_resta(restaList);
				resp.setContent_detail_cmt(cmtList);
				resp.setTags(tagList);
				resp.setTag_name(tagListResult);
				resp.setTag_name_area(tagAreaListResult);

				return resp;
		
	}

	// 코스검색
	public List<CourseDTO> search_course(String subject, String nickname, String tag) {
		List<CourseDTO> resp = dao.search_course(subject, nickname, tag);

		for (CourseDTO course : resp) {
			List<CourseTagDTO> tags = dao.getTags(course.getPost_idx());
			course.setTags(tags);
		
		List<TagDTO> tagsName =new ArrayList<>();
		List<TagAreaDTO>tagsAreaName = new ArrayList<>();
		
		
		for (CourseTagDTO tagInfo : tags) {
			if("tag".equals(tagInfo.getIsClass())) {
				tagsName.addAll(dao.searchTags(tagInfo.getIdx()));
			}else if("area_tag".equals(tagInfo.getIsClass())) {
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
		res = new FileSystemResource("C:/upload/"+fileName);
		
		// 3. photo 냐 download 냐 에 따라 Header 를 설정 해 준다.
		try {
			if(type.equals("photo")) {
				String content_type = Files.probeContentType(Paths.get("C:/upload/"+fileName));
				headers.add("Content-Type", content_type);
			}else {
//				headers.add("Content-Type", "application/octet-stream");		
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}				
		//resource, header,status
		return new ResponseEntity<Resource>(res,headers,HttpStatus.OK);
	}

}
