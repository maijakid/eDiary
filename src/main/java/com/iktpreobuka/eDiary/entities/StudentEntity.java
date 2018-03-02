package com.iktpreobuka.eDiary.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class StudentEntity extends PersonEntity{
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "department"),	//pogresno je imenovao tabele u mysql-u
		@JoinColumn(name = "schoolClass")
	})
	private DepartmentEntity schoolClassDepartment;

	
	
	
	
	public DepartmentEntity getSchoolClassDepartment() {
		return schoolClassDepartment;
	}

	public void setSchoolClassDepartment(DepartmentEntity schoolClassDepartment) {
		this.schoolClassDepartment = schoolClassDepartment;
	}
	
	
	
	
	
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	public Long id;
//	
//	@Column(nullable = false)
//	public String name;
//	
//	@Column(nullable = false)
//	public String surname;
//	
//	
//	public StudentEntity() {
//		super();
//	}
//
//
//	public Long getId() {
//		return id;
//	}
//
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//
//	public String getName() {
//		return name;
//	}
//
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//
//	public String getSurname() {
//		return surname;
//	}
//
//
//	public void setSurname(String surname) {
//		this.surname = surname;
//	}
	
	

}
