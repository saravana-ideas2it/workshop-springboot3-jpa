package com.ideas2it.workshop.controllers;

import java.util.List;

import com.ideas2it.workshop.entities.Role;
import com.ideas2it.workshop.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {
	
	@Autowired
	private RoleService service;
	
	@GetMapping
	public ResponseEntity<List<Role>> findAll() {
		List<Role> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Role> findById(@PathVariable Long id){
		Role obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
