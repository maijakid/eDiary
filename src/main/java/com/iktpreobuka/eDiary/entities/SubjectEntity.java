package com.iktpreobuka.eDiary.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String subject;
	private Integer fund;
	
	
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
	
	
	
	
	
	
}
