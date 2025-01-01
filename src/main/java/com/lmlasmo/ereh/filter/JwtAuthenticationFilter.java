package com.lmlasmo.ereh.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lmlasmo.ereh.model.Users;
import com.lmlasmo.ereh.repository.UsersRepository;
import com.lmlasmo.ereh.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private UsersRepository usersRepository;
	private JwtService jwtService;
	
	@Autowired
	public JwtAuthenticationFilter(UsersRepository usersRepository, JwtService jwtService) {
		this.usersRepository = usersRepository;
		this.jwtService = jwtService;		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(request.getRequestURI().contains("/login")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = getToken(request);		
				
		Optional<Users> user = getUser(token);			
		
		if(user.isEmpty()) {
			filterChain.doFilter(request, response);
			return;
		}
		
		Collection<? extends GrantedAuthority> roles = user.get().getAuthorities();		
		
		Authentication auth = UsernamePasswordAuthenticationToken.authenticated(user.get(), null, roles);		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);				
	}
	
	protected String getToken(HttpServletRequest request) {
		
		String auth = request.getHeader("Authorization");
		
		if(auth != null) {			
			auth = auth.replace("Bearer ", "");						
		}
		
		return auth;		
	}
	
	protected boolean verifyToken(String token) {
		
		if(token == null) {
			return false;
		}
		
		return jwtService.isTokenValid(token);
	}
	
	protected Optional<Users> getUser(String token){
		
		Optional<Users> user = Optional.ofNullable(null);
		
		if(verifyToken(token)) {
			user = usersRepository.findByUsername(jwtService.getUsername(token));
		}
		
		if(user.isPresent()) {
			if(user.get().isLocked()) {
				user = Optional.ofNullable(null);
			}
		}		
		
		return user;
	}

}
