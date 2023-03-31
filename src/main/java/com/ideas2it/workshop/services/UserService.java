package com.ideas2it.workshop.services;

import java.util.List;
import java.util.Optional;

import com.ideas2it.workshop.entities.SearchParameters;
import com.ideas2it.workshop.entities.User;
import com.ideas2it.workshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ideas2it.workshop.services.exceptions.DatabaseException;
import com.ideas2it.workshop.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public Page<User> findAll(SearchParameters params){
		PageRequest pageable = PageRequest.of(
			params.getPage(),
			params.getLimit(),
			Sort.by(params.getSort())
		);

		return repository.findAll(pageable);
	}
	
	public User findById(Long id) {
		Optional<User>obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
	 try {
		repository.deleteById(id);
	}catch(EmptyResultDataAccessException e){
		throw new ResourceNotFoundException(id);
	}catch(DataIntegrityViolationException e) {
		throw new DatabaseException(e.getMessage());
	}
}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj, false);
			return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}


	public User patch(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj, true);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj, Boolean patch) {
		if (!patch)
			entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}	
}
