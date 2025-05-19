package com.eat.mypage;

public class TagPreferDTO {

	// tag_prefer
		private int idx;
		private String isClass; //class에서 isClass로 바꿈(일반/지역 태그 분류)

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
