package com.lmlasmo.ereh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ereh.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{
	
	public Optional<Position> findByName(String name);
	
	public List<Position> findByNameContaining(String name);
	
	public Page<Position> findByNameContaining(String name, Pageable pageable);
	
	public List<Position> findByDepartmentId(int id);

	public Page<Position> findByDepartmentId(int id, Pageable pageable);
	
	public boolean existsByIdAndName(long id, String name);
	
	public boolean existsByName(String name);	
	
}
