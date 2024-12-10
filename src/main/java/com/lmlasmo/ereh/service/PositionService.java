package com.lmlasmo.ereh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.ereh.dto.PositionDTO;
import com.lmlasmo.ereh.model.Position;
import com.lmlasmo.ereh.repository.PositionRepository;

@Service
@Transactional
public class PositionService {
	
	private PositionRepository positionRepository;
	
	@Autowired
	public PositionService(PositionRepository positionRepository) {
		this.positionRepository = positionRepository;
	}
	
	private PositionDTO save(Position position) {		
		
		positionRepository.save(position);
		
		return new PositionDTO(position);		
	}
	
	public PositionDTO save(PositionDTO dto) {
		
		Position position = new Position(dto);
		
		return save(position);				
	}	

	public Optional<PositionDTO> findByName(String name) {
		
		Optional<Position> position = positionRepository.findByName(name);		
		
		return position.map(p -> new PositionDTO(p));
	}	
	
	public List<PositionDTO> findByNameContaining(String name) {
		
		List<PositionDTO> dtoList = positionRepository.findByNameContaining(name)
				.stream()
				.map(p -> new PositionDTO(p))
				.toList();	
				
		return dtoList;
	}
	
	public Page<PositionDTO> findByNameContaining(String name, Pageable pageable) {
		
		Page<PositionDTO> dtoPage = positionRepository.findByNameContaining(name, pageable)				
				.map(p -> new PositionDTO(p));				
				
		return dtoPage;
	}
	
	public List<PositionDTO> findAll() {
		
		List<PositionDTO> dtoList = positionRepository.findAll()
				.stream()
				.map(p -> new PositionDTO())
				.toList();
		
		return dtoList;	
	}
	
	public Page<PositionDTO> findAll(Pageable pageable) {
		
		Page<PositionDTO> dtoPage = positionRepository.findAll(pageable)				
				.map(p -> new PositionDTO());
		
		return dtoPage;
	}
	
	public List<PositionDTO> findByDepartmentId(int id){
		
		List<PositionDTO> dtoList = positionRepository.findByDepartmentId(id)
				.stream()
				.map(p -> new PositionDTO(p))
				.toList();
		
		return dtoList;		
	}
	
	public Page<PositionDTO> findByDepartmentId(int id, Pageable pageable){
		
		Page<PositionDTO> dtoPage = positionRepository.findByDepartmentId(id, pageable)				
				.map(p -> new PositionDTO(p));
		
		return dtoPage;		
	}
	
	public boolean deleteById(long id) {
		
		positionRepository.deleteById(id);
		
		return !positionRepository.existsById(id);
		
	}
	
	public boolean existsById(long id) {
		return positionRepository.existsById(id);
	}
	
	public boolean existsByName(String name) {
		return positionRepository.existsByName(name);
	}
	
	public boolean existsByIdAndName(long id, String name) {
		return positionRepository.existsByIdAndName(id, name);
	}	
	
}
