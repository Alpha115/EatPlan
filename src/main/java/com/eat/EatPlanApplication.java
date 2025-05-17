package com.eat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eat.utils.JwtUtil;

@SpringBootApplication
public class EatPlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatPlanApplication.class, args);
		
		// pri_key가 없을 경우 생성
		if(JwtUtil.getPri_key()==null) {
			JwtUtil.setPri_key();
		}
	}

}
