package com.iktpreobuka.eDnevnik.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iktpreobuka.eDnevnik.enums.EnumClass;

@Entity
public class SubjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String subject;
	private Integer fund;
	private EnumClass forClass;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "teachers_teaching_subjects", joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"))
//	@JsonManagedReference
	private Set<TeacherEntity> teachersOnSubject;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "school_class")
	private ClassEntity schoolClass;
	
	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Set<GradeEntity> gradesOnSubjects;
	
	
	
	
	
	
	
	
	
	
	public SubjectEntity() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public Integer getFund() {
		return fund;
	}


	public void setFund(Integer fund) {
		this.fund = fund;
	}


	public EnumClass getForClass() {
		return forClass;
	}


	public void setForClass(EnumClass forClass) {
		this.forClass = forClass;
	}


	
	
	public Set<TeacherEntity> getTeachersOnSubject() {
		return teachersOnSubject;
	}


	public void setTeachersOnSubject(Set<TeacherEntity> teachersOnSubject) {
		this.teachersOnSubject = teachersOnSubject;
	}


	public ClassEntity getSchoolClass() {
		return schoolClass;
	}


	public void setSchoolClass(ClassEntity schoolClass) {
		this.schoolClass = schoolClass;
	}


	public Set<GradeEntity> getGradesOnSubjects() {
		return gradesOnSubjects;
	}


	public void setGradesOnSubjects(Set<GradeEntity> gradesOnSubjects) {
		this.gradesOnSubjects = gradesOnSubjects;
	}
	
	
	
	
	
	
}
