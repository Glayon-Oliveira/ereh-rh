package com.lmlasmo.ereh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ereh.model.Users;

public interface UsersRepository extends JpaRepository<Users, String>{

	public Optional<Users> findByUsername(String username);
	
	public List<Users> findByLocked(boolean locked);
	
	public Page<Users> findByLocked(boolean locked, Pageable pageable);	
	
	public void deleteByUsername(String username);
	
}
