package com.lmlasmo.ereh.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Service
public class JwtService {
	
	@Value("EREH_JWT_KEY")
	private String JWT_KEY;
	
	@Value("EREH_JWT_ISSUER")
	private String ISSUER;

	public JwtService() {}
	
	public String gerateToken(String username, List<String> roles) {
		
		Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);
		
		Date issued = new Date();
		Date expires = new Date(System.currentTimeMillis()+60*60*1000);
		
		return JWT.create()
				  .withIssuer(ISSUER)
				  .withSubject(username)				  
				  .withIssuedAt(issued)
				  .withExpiresAt(expires)
				  .sign(algorithm);		 		
	}
	
	public boolean isTokenValid(String token) {
		
		Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);
		
		DecodedJWT jwt = JWT.decode(token);
		
		JWTVerifier verify = JWT.require(algorithm)
								 .withIssuer(ISSUER)								 
								 .build();
		
		try {
			verify.verify(jwt);										
			return jwt.getSubject() != null;
		}catch(Exception e) {
			return false;
		}
				
	}
	
	public String getUsername(String token) {
		
		if(isTokenValid(token)) {
			return JWT.decode(token).getSubject();
		}
		
		return null;
	}
	
}
