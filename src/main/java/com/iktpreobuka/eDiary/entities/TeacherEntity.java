package com.iktpreobuka.eDiary.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TeacherEntity extends PersonEntity{

	@JsonIgnore
	@ManyToMany(mappedBy = "teachersOnSubject")
	/*@JoinTable(name = "teachers_teaching_subjects", joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))*/
	private Set<SubjectEntity> teacherOnSubjects;

	public Set<SubjectEntity> getTeacherOnSubjects() {
		return teacherOnSubjects;
	}

	public void setTeacherOnSubjects(Set<SubjectEntity> teacherOnSubjects) {
		this.teacherOnSubjects = teacherOnSubjects;
	}
	
	
	
	
}
