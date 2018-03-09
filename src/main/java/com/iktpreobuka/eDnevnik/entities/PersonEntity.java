package com.iktpreobuka.eDnevnik.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.fasterxml.jackson.annotation.JsonSubTypes;


import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME/*CLASS*/, include = JsonTypeInfo.As.PROPERTY, property = /*"@class"*/"type")	//pokusaj 
@JsonSubTypes({ @Type(value = StudentEntity.class, name = "student"),
				@Type(value = TeacherEntity.class, name = "teacher"),
				@Type(value = ParentEntity.class, name = "parent"),
})
@MappedSuperclass
public abstract class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname;
	
	
	public PersonEntity() {}

	
	
	
	
//	public PersonEntity(Long id, String name, String surname) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.surname = surname;
//	}





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
