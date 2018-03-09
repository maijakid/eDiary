package com.iktpreobuka.eDnevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDTO {

	
	private String name;
	private String surname;
	
	
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("surname")
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public PersonDTO(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}
	public PersonDTO() {
		super();
	}
	
	
	
	
	
	
}
