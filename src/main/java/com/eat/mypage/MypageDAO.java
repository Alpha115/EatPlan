package com.eat.mypage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.eat.dto.CourseDTO;
import com.eat.dto.MypageDTO;
import com.eat.dto.PhotoDTO;
import com.eat.dto.TagPreferDTO;

@Mapper
public interface MypageDAO {
	
	// 회원정보 불러오기
	List<MypageDTO> member_list(String user_id);
	
	// 태그 불러오기
	List<TagPreferDTO> member_tag_list(String user_id);

	// 회원정보 수정
	boolean member_update(MypageDTO dto);

	// 회원 탈퇴
	int member_secession(Map<String, String> params);

	// 회원 정보 수정 - 태그삭제
	void deletMemberTags(@Param("user_id")String user_id,@Param("tagList") List<String> removeTags);

	// 회원 정보 수정 - 태그추가
	void addMemberTags(@Param("user_id") String user_id,@Param("tagList") List<String> addTags);
	
	//프로필 정보 수정
	int profile_update(MypageDTO dto);
	
	//사진 정보 DB에 저장
	int saveProfileImg(PhotoDTO photoDTO);

	// 프로필 삭제
	int profile_del(int img_idx);
	
	// 사진 정보 조회
	List<Map<String, String>> photo_del(int img_idx);

	// 프로필 정보 가져오기
	Map<String, String> imgInfo(int img_idx);
	
	// 비밀번호 확인
	String member_pass(String user_id);

	// 내가 쓴 게시글 모아보기
	List<CourseDTO> my_course_list(String user_id, int offset);

	// 내가 좋아요 한 글 모아보기
	List<CourseDTO> like_course_list(String user_id, int offset);

	int pages(String user_id);

	int liked_pages(String user_id);

	// 이메일 중복 체크
	int overlayEmail(String email);

	// 마이페이지 비밀번호 변경 (기존 비밀번호, 새 비밀번호)
	int mypage_updatePassword(String user_id, String existing_pass, String new_pass);

	// 유저가 선택한 태그 이름 조회 (아래 두 개)
	List<TagPreferDTO> member_tag_idx(String user_id);

	List<String> getTagNamesFromTagArea(List<Integer> areaTagIdxList);

    List<String> getTagNamesFromTag(List<Integer> generalTagIdxList);

}
