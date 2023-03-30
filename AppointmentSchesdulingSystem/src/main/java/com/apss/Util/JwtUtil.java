package com.apss.Util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.apss.Exception.AccessDeniedException;
import com.apss.Model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	private static String secret = "this_is_secret";
	private static long expiryDuration = 60 * 60;

	public String generateJwt(User user) {

		// setting expiry time for token
		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;

		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		Claims claims = Jwts.claims().setIssuer(Integer.toString(user.getUserId())).setIssuedAt(issuedAt);

		/*
		 * At some places we will login as a admin or normal user
		 */
		// claims.put("type", user.getUserType);

		// this will added in the payload
		claims.put("name", user.getUserFirstName());
		claims.put("email_", user.getEmail());

		// generate jwt using claims
		// return Jwts.builder().setClaims(claims).compact();
		return Jwts.builder().signWith(SignatureAlgorithm.HS512, secret).setClaims(claims).compact();
	}

	public void verify(String authentication) throws Exception {

		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authentication);
		} catch (Exception e) {
			// throw new Exception();
			throw new AccessDeniedException("Access Denied......");
		}
	}
}
