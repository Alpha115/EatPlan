package com.eat.dto;

public class TagPreferDTO {

	// tag_prefer
		private int idx;
		private String isClass; //class에서 isClass로 바꿈(일반/지역 태그 분류)
		private String user_id;
		
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public int getIdx() {
			return idx;
		}
		public void setIdx(int idx) {
			this.idx = idx;
		}
		public String getIsClass() {
			return isClass;
		}
		public void setIsClass(String isClass) {
			this.isClass = isClass;
		}
	
	
	
}
