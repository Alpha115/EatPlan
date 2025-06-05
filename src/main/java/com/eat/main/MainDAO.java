package com.eat.main;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eat.dto.CourseDTO;
import com.eat.dto.CourseTagDTO;
import com.eat.dto.DetailCmtDTO;
import com.eat.dto.DetailRestaDTO;
import com.eat.dto.LikedDTO;
import com.eat.dto.MainDTO;
import com.eat.dto.MemberDTO;
import com.eat.dto.PhotoDTO;
import com.eat.dto.RestaurantDTO;
import com.eat.dto.StarDTO;
import com.eat.dto.TimelineDTO;
import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagDTO;

@Mapper
public interface MainDAO {

	// 코스 리스트 불러오기
	List<CourseDTO> course_list(@Param("offset") int offset, @Param("limit") int limit, @Param("sort") String sort); // 코스 리스트 몸통
	String course_list_nick(String user_id); // 코스 리스트 닉네임
	int course_list_cmt_cnt(int post_idx); // 코스 리스트 댓글 수
	int course_list_like_cnt(int post_idx); // 코스 리스트 좋아요 수
	int course_list_star_avg(int post_idx); // 코스 리스트 별점 평균
	List<String> course_list_tag(int post_idx); // 코스 리스트 태그
	List<TagAreaDTO> course_list_tag_area(int post_idx); // 코스 리스트 지역태그
	List<DetailRestaDTO> detail(int post_idx); // 코스 리스트 세부일정 idx
	String course_list_img(int detail_idx); // 코스 리스트 이미지
	int totalCount(); // 전체 페이지 수
	
	// 코스 리스트 페이지 처리
	int pages(int limit);


	
	//코스 상세보기
	public CourseDTO getCourseDTO(int post_idx);
	int setB_hit(int post_idx);
	public TimelineDTO getTimelineDTO(int post_idx);
	MemberDTO getNickname(String user_id);
	public List<DetailRestaDTO> getDetailRestaList(int post_idx);
	public List<DetailCmtDTO> getCmtDTOList(int post_idx);
	public List<CourseTagDTO> getCourseList(int post_idx);
	List<TagDTO> course_tags(int idx);
	List<TagAreaDTO> course_list_tags_area(int idx);
	RestaurantDTO getRestaurantByIdx(int rIdx);
	PhotoDTO getPhotoByImgIdx(int img_idx);
	
	
	//코스 검색
	List<CourseDTO> search_course(@Param("subject") String subject, 
			@Param("nickname")String nickname, 
			@Param("tag")String tag);
	Collection<? extends TagDTO> searchTags(int idx);
	Collection<? extends TagAreaDTO> searchTagsArea(int idx);
	List<PhotoDTO>  getPhotosByPostIdx(@Param("post_idx") int post_idx);
	String courseListImg(@Param("post_idx") int post_idx);
	
	//코스 태그 가져오기
	List<CourseTagDTO> getTags(int post_idx);
	
	// 코스 전체 리스트 불러오기 (전체)
	List<MainDTO> course_list_all();

	// 사진
	String fileInfo(String new_filename);
	String findFileName(int img_idx);
	
	// 좋아요
	Boolean checkPostLike(LikedDTO params);
	int insertPostLike(LikedDTO params);
	int updatePostLike(LikedDTO params);
	Boolean checkCmtLike(LikedDTO params);
	int insertCmtLike(LikedDTO params);
	int updateCmtLike(LikedDTO params);
	
	// 좋아요 체크
	Boolean likeCheck(String user_id, int post_idx);
	
	// 별점
	int star(StarDTO params);
	
	// 좋아요 체크 댓글
	 List<Map<String, Object>> likeCheckCmt(@Param("user_id") String user_id, @Param("list") List<Integer> cmtIdxList);
	
	
}
