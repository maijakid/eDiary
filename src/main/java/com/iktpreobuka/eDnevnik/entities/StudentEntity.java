package com.iktpreobuka.eDnevnik.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class StudentEntity extends PersonEntity{
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "department"),	//pogresno je imenovao tabele u mysql-u
		@JoinColumn(name = "schoolClass")
	})
	private DepartmentEntity schoolClassDepartment;

	
	
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Set<GradeEntity> gradesForStudent;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy = "child")
//	@JsonManagedReference
	private Set<ParentEntity> parent;
	
	
	
	public DepartmentEntity getSchoolClassDepartment() {
		return schoolClassDepartment;
	}

	public void setSchoolClassDepartment(DepartmentEntity schoolClassDepartment) {
		this.schoolClassDepartment = schoolClassDepartment;
	}

	public Set<GradeEntity> getGradesForStudent() {
		return gradesForStudent;
	}

	public void setGradesForStudent(Set<GradeEntity> gradesForStudent) {
		this.gradesForStudent = gradesForStudent;
	}

	public Set<ParentEntity> getParent() {
		return parent;
	}

	public void setParent(Set<ParentEntity> parent) {
		this.parent = parent;
	}
	
	@JsonCreator
    public StudentEntity() {}
	
	
	
	
/*	@JsonCreator
    public StudentEntity(@JsonProperty("id")Long id, @JsonProperty("name")String name, @JsonProperty("surname")String surname) {
        super(id, name, surname);
//        this.itemAProperty1 = p1;
//        this.itemAProperty2 = p2;
    }*/
	
	
	
	
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
