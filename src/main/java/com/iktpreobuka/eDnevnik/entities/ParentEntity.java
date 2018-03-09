package com.iktpreobuka.eDnevnik.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ParentEntity extends PersonEntity {

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "parents_children", joinColumns = @JoinColumn(name = "parent_id", referencedColumnName = "id"), 
									inverseJoinColumns = @JoinColumn(name = "child_id", referencedColumnName = "id"))
//	@JsonBackReference		//sa back i managed reference vraca onaj utf8 error
	private Set<StudentEntity> child;

	
	
	
	public Set<StudentEntity> getChild() {
		return child;
	}

	public void setChild(Set<StudentEntity> child) {
		this.child = child;
	}
	
	
	
	
	
}
