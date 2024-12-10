package com.lmlasmo.ereh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.ereh.dto.UserDTO;
import com.lmlasmo.ereh.dto.register.SignupDTO;
import com.lmlasmo.ereh.model.Employee;
import com.lmlasmo.ereh.model.Position;
import com.lmlasmo.ereh.model.Users;
import com.lmlasmo.ereh.repository.UsersRepository;

@Service
@Transactional
public class UserService {

	private UsersRepository usersRepository;
	
	@Autowired
	public UserService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public UserDTO save(Users user) {
		
		user = usersRepository.save(user);
		
		return new UserDTO(user);		
	}
	
	public UserDTO save(SignupDTO signup) {
				
		Users user = new Users();		
				
		user.setUsername(signup.getUsername());
		user.setPassword(signup.getPassword());				
		user.setEmployee(new Employee(signup.getEmployee()));		
				
		Position position = new Position();			
		position.setId(signup.getPosition());			
				
		user.setPosition(position);		
		
		return save(user);		
	}	
	
	public Optional<UserDTO> findByUsername(String username) {
		
		Optional<Users> users = usersRepository.findByUsername(username);
		
		return users.map(u -> new UserDTO(u)); 
	}

	public Optional<UserDTO> findById(long id) {
		
		Optional<Users> users = usersRepository.findById(id);
		
		return users.map(u -> new UserDTO(u)); 		
	}
	
	public List<UserDTO> findAll(){
		
		List<UserDTO> dtoList = usersRepository.findAll()
				.stream()
				.map(u -> new UserDTO(u))
				.toList();
		
		return dtoList;
	}
	
	public Page<UserDTO> findAll(Pageable pageable){
		
		Page<UserDTO> dtoPage = usersRepository.findAll(pageable)				
				.map(u -> new UserDTO(u));
		
		return dtoPage;
	}
	
	public Optional<UserDTO> findByEmployee(long employee) {
		
		Optional<UserDTO> dtoList = usersRepository.findByEmployeeId(employee)
				.map(u -> new UserDTO(u));
		
		return dtoList;
	}
	
	public List<UserDTO> findByPosition(long position) {

		List<UserDTO> dtoList = usersRepository.findByPositionId(position)
				.stream()
				.map(u -> new UserDTO(u))
				.toList();
		
		return dtoList;
	}

	public Page<UserDTO> findByPosition(long position, Pageable pageable) {

		Page<UserDTO> dtoPage = usersRepository.findByPositionId(position, pageable)				
				.map(u -> new UserDTO(u));
		
		return dtoPage;
	}
	
	public List<UserDTO> findByDepartment(int position) {

		List<UserDTO> dtoList = usersRepository.findByPositionDepartmentId(position)
				.stream()
				.map(u -> new UserDTO(u))
				.toList();
		
		return dtoList;
	}

	public Page<UserDTO> findByDepartment(int position, Pageable pageable) {

		Page<UserDTO> dtoPage = usersRepository.findByPositionDepartmentId(position, pageable)				
				.map(u -> new UserDTO(u));
		
		return dtoPage;
	}

	public Page<UserDTO> findByLockedUser(boolean locked, Pageable pageable) {		
		
		Page<UserDTO> users = usersRepository.findByLocked(locked, pageable)				
				.map(u -> new UserDTO(u));				
		
		return users;
	}
	
	public List<UserDTO> findByLockedUser(boolean locked) {		
		
		List<UserDTO> users = usersRepository.findByLocked(locked)
				.stream()
				.map(u -> new UserDTO(u))
				.toList();
		
		return users;
	}
	
	public boolean deleteById(long id) {
		
		Optional<Users> user = usersRepository.findById(id);
		
		if(user.isPresent()) {
			usersRepository.delete(user.get());
			
			return !usersRepository.existsById(id);			
		}
		
		return false;
		
	}	
	
	public boolean existsByUsername(String username) {
		return usersRepository.existsByUsername(username);
	}

	public boolean existsById(long id) {
		return usersRepository.existsById(id);
	}	
	
}
