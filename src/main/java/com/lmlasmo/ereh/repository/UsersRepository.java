package com.lmlasmo.ereh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ereh.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	public Optional<Users> findByUsername(String username);
	
	public List<Users> findByLocked(boolean locked);
	
	public Page<Users> findByLocked(boolean locked, Pageable pageable);
	
	public List<Users> findByPositionId(long position);
	
	public Page<Users> findByPositionId(long position, Pageable pageable);
	
	public List<Users> findByPositionDepartmentId(int department);
	
	public Page<Users> findByPositionDepartmentId(int department, Pageable pageable);
	
	public void deleteByUsername(String username);

	public boolean existsByUsername(String username);	
	
}
