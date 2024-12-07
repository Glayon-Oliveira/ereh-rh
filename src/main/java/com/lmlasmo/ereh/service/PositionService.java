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
		
		Position entity = positionRepository.save(position);
		
		if(entity.getId() != position.getId()) {
			return new PositionDTO(position);
		}
		
		 return null;		
	}
	
	public PositionDTO save(PositionDTO dto) {
		
		Position position = new Position(dto);
		
		return save(position);				
	}	

	public PositionDTO findByName(String name) {
		
		Optional<Position> position = positionRepository.findByName(name);
		
		if(position.isPresent()) {
			return new PositionDTO(position.get());
		}
		
		return null;
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
	
	public boolean deleteById(long id) {
		
		positionRepository.deleteById(id);
		
		return !positionRepository.existsById(id);
		
	}
	
	public boolean existsByName(String name) {
		return positionRepository.existsByName(name);
	}
	
	public boolean existsByIdAndName(long id, String name) {
		return positionRepository.existsByIdAndName(id, name);
	}
	
}
