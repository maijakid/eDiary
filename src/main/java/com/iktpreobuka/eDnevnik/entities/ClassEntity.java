package com.iktpreobuka.eDnevnik.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



import com.iktpreobuka.eDnevnik.enums.EnumClass;
import com.iktpreobuka.eDnevnik.entities.SubjectEntity;

@Entity
public class ClassEntity {

	@Id
	private EnumClass schoolClass;
	
	@OneToMany(mappedBy = "schoolClass", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private Set<SubjectEntity> subjectsInClass;

	public EnumClass getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(EnumClass schoolClass) {
		this.schoolClass = schoolClass;
	}

	public Set<SubjectEntity> getSubjectsInClass() {
		return subjectsInClass;
	}

	public void setSubjectsInClass(Set<SubjectEntity> subjectsInClass) {
		this.subjectsInClass = subjectsInClass;
	}

	

	
	
	
	
}
