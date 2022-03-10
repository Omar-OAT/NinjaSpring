package com.project.ninjas.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		this.service = service;

	}

	@PostMapping("/create")
	public ResponseEntity<Ninja> createNinja(@RequestBody Ninja n) {
		Ninja created = this.service.create(n);
		ResponseEntity<Ninja> response = new ResponseEntity<Ninja>(created, HttpStatus.CREATED);
		return response;

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Ninja>> getAllNinjas() {
		return ResponseEntity.ok(this.service.getAll());
	}

	@GetMapping("/get/{id}") // 200
	public Ninja getNinja(@PathVariable Integer id) {

		return this.service.getNinja(id);
	}

	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Ninja>> getNinjaByName(@PathVariable String name) {
		List<Ninja> found = this.service.getAllNinjasByName(name);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getByAge/{age}")
	public ResponseEntity<List<Ninja>> getNinjaByAge(@PathVariable Integer age) {
		List<Ninja> found = this.service.getAllNinjasByAge(age);
		return ResponseEntity.ok(found);

	}

	@GetMapping("/getByVillage/{village}")
	public ResponseEntity<List<Ninja>> getNinjaByVillage(@PathVariable String village) {
		List<Ninja> found = this.service.getAllNinjasByVillage(village);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getByJutsu/{jutsu}")
	public ResponseEntity<List<Ninja>> getNinjaByJutsu(@PathVariable String jutsu) {
		List<Ninja> found = this.service.getAllNinjasByJutsu(jutsu);
		return ResponseEntity.ok(found);
	}
	
	@PutMapping("/replace/{id}")
	public ResponseEntity<Ninja> replaceNinja(@PathVariable Integer id, @RequestBody Ninja newNinja){
		Ninja body = this.service.replace(id, newNinja);
		ResponseEntity<Ninja> response = new ResponseEntity<Ninja>(body, HttpStatus.ACCEPTED);
		return response;
		
	}
	@DeleteMapping("/remove/{id}") 
	public ResponseEntity<?> deleteNinja(@PathVariable Integer id){
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
	}
}
