package com.project.ninjas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ninjas.domain.Ninja;
import com.project.ninjas.service.NinjaService;

@RestController
public class NinjaController {

	private NinjaService service;
	@Autowired
	public NinjaController(NinjaService service) {
		super();
		this.service=service;
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<Ninja> createDog(@RequestBody Ninja n){
		Ninja created = this.service.cr
		
	}
}
