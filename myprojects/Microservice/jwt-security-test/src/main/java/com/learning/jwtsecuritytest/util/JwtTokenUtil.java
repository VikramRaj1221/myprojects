package com.learning.jwtsecuritytest.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

@Service
public class JwtTokenUtil {

	@Value("${jwt.secret.key}")
	private String key;
	
	@Value("${jwt.token.expire.time.seconds}")
	private Integer expireTimeInSec;
	
	private Clock clock=DefaultClock.INSTANCE;
	
	public String generateToken(String userName) {
		Map<String,Object> claims=new HashMap<String,Object>();
		return Jwts.builder().addClaims(claims).setSubject(userName).setIssuedAt(clock.now())
				.setExpiration(getExpirationDate(clock.now())).signWith(SignatureAlgorithm.HS256, key).compact();
	}
	
	public String getUserName(String token) {
		Claims clims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		return clims.getSubject();
	}
	
	public boolean IstokenValid(String token) {
		Date expireationDate=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getExpiration();
		if(clock.now().before(expireationDate)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public Date getExpirationDate(Date date) {
		return new Date(date.getTime() + expireTimeInSec * 1000);
	}
	
}
