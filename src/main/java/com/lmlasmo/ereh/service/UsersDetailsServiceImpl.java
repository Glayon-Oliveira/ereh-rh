package com.lmlasmo.ereh.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lmlasmo.ereh.model.Users;
import com.lmlasmo.ereh.repository.UsersRepository;

@Service
public class UsersDetailsServiceImpl implements UserDetailsService {
	
	private UsersRepository repository;
	
	public UsersDetailsServiceImpl(UsersRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> user = repository.findByUsername(username);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UsernameNotFoundException("User not found");		
	}

}
