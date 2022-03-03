package com.project.ninjas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.ninjas.domain.Ninja;
import com.project.ninjas.repo.NinjaRepo;

@Service
public class NinjaService {
	private NinjaRepo repo;

	@Autowired
	public NinjaService(NinjaRepo repo) {

		super();
		this.repo = repo;

	}

	public Ninja create(Ninja n) {
		Ninja created = this.repo.save(n);
		return created;

	}

	public List<Ninja> getAll() {
		return this.repo.findAll();

	}

	public List<Ninja> getAllNinjasByName(String name) {

		List<Ninja> found = this.repo.findByNameIgnoreCase(name);
		return found;
	}

	public List<Ninja> getAllNinjasByVillage(String village) {
		List<Ninja> found = this.repo.findByVillage(village);
		return found;
	}

	public List<Ninja> getAllNinjasByAge(Integer age) {
		List<Ninja> found = this.repo.findByAge(age);
		return found;

	}

	public List<Ninja> getAllNinjasByJutsu(String Jutsu) {
		List<Ninja> found = this.repo.findByJutsu(Jutsu);
		return found;

	}

	public Ninja getNinja(Integer id) {
		Optional<Ninja> found = this.repo.findById(id);
		return found.get();
	}
	public Ninja replace(Integer id, Ninja newNinja) {
		Ninja Ninupdate = this.repo.findById(id).get();
		Ninupdate.setAge(newNinja.getAge());
		Ninupdate.setVillage(newNinja.getVillage());
		Ninupdate.setName(newNinja.getName());
		Ninupdate.setJutsu(newNinja.getJutsu());
		Ninja updated = this.repo.save(Ninupdate);
		return updated;
		
	}
	public void remove(@PathVariable Integer id) {
		this.repo.deleteById(id);
	}
}
