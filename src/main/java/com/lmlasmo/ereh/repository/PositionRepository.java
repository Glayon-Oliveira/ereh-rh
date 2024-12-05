package com.lmlasmo.ereh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ereh.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{
	
	public Optional<Position> findByName(String name);
	
	public List<Position> findByNameContaining(String name);
	
}
