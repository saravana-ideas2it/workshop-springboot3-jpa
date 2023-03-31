package com.ideas2it.workshop.services;

import java.util.List;
import java.util.Optional;

import com.ideas2it.workshop.entities.Role;
import com.ideas2it.workshop.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	public List<Role> findAll(){
		return repository.findAll();
	}
	
	public Role findById(Long id) {
		Optional<Role>obj = repository.findById(id);
		return obj.get();
	}
}
