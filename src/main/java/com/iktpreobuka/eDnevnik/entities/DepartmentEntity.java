package com.iktpreobuka.eDnevnik.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;

import com.iktpreobuka.eDnevnik.enums.EnumDepartment;
import com.iktpreobuka.eDnevnik.enums.EnumClass;
import com.iktpreobuka.eDnevnik.enums.SchoolClassDepartment;


@Entity
@IdClass(SchoolClassDepartment.class)
public class DepartmentEntity {

	@Id
	private EnumClass schoolClass;
	@Id
	private EnumDepartment department;
	
	@OneToMany(/*mappedBy = "class_department", */fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Set<StudentEntity> studentsInDepatment;
	
	
	
	
	
	public EnumClass getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(EnumClass schoolClass) {
		this.schoolClass = schoolClass;
	}
	public EnumDepartment getDepartment() {
		return department;
	}
	public void setDepartment(EnumDepartment department) {
		this.department = department;
	}
	public Set<StudentEntity> getStudentsInDepatment() {
		return studentsInDepatment;
	}
	public void setStudentsInDepatment(Set<StudentEntity> studentsInDepatment) {
		this.studentsInDepatment = studentsInDepatment;
	}
	
	
	
	
	
	
}
