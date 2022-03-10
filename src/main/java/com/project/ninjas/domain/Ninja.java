package com.project.ninjas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ninja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String name;
	private Integer age;
	private String village;
	private String jutsu;
	
	
	
	
	@Override
	public String toString() {
		return "Ninja [id=" + id + ", name=" + name + ", age=" + age + ", village=" + village + ", jutsu=" + jutsu
				+ "]";
	}
	
	
	
	



	public Ninja(Integer id, String name, Integer age, String village, String jutsu) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.village = village;
		this.jutsu = jutsu;
	}



public Ninja() {
	super();
}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getJutsu() {
		return jutsu;
	}
	public void setJutsu(String jutsu) {
		this.jutsu = jutsu;
	}
	
	
	
	
}
