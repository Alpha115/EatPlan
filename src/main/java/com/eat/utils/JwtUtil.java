package com.eat.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	private static SecretKey pri_key = null;

	public static SecretKey getPri_key() {
		return pri_key;
	}

	// key를 생성합니다.
	public static void setPri_key() {
		JwtUtil.pri_key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}

	// 토큰을 생성합니다. 토큰 유지시간은 24시간입니다.
	public static String getToken(Map<String, Object> map) {
		String token = Jwts.builder().setHeaderParam("alg", "HS256").setHeaderParam("typ", "JWT").setClaims(map)
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
				.signWith(pri_key).compact();
		return token;
	}

	// ▲오버로드, 값 1개만 들어왔을 경우
	public static String getToken(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return getToken(map);
	}

	// 토큰 검증
	public static Map<String, Object> readToken(String token) {
		Map<String, Object> result = new HashMap<String, Object>();
		Claims claims = Jwts.parserBuilder().setSigningKey(pri_key).build().parseClaimsJws(token).getBody();

		for (String key : claims.keySet()) {
			result.put(key, claims.get(key));
		}

		return result;
	}

	// 토큰 확인 메소드

//	String loginId = (String) JwtUtil.readToken(header.get("authorization")).get("user_id");
//	if (loginId.equals("")) {
//		/*코드입력*/
//	}

}
