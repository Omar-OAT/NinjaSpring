package com.project.ninjas.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.ninjas.domain.Ninja;

@Repository
public interface NinjaRepo extends JpaRepository<Ninja, Integer>  {

	List<Ninja> findByNameIgnoreCase(String name);
	List<Ninja> findByAge(Integer age);
	List<Ninja> findByVillage(String village);
	List<Ninja> findByJutsu(String jutsu);
	
	
	
}
