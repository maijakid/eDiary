package com.iktpreobuka.eDiary.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.iktpreobuka.eDiary.enums.EnumGrade;



@Entity
public class GradeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;		//da li mi treba id? da li je entitet?
	private EnumGrade grade;
	private String comment;
	private Date date;
	private Boolean finalGrade;
//	private Integer semester; //neiskoriscena jos
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EnumGrade getGrade() {
		return grade;
	}
	public void setGrade(EnumGrade grade) {
		this.grade = grade;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Boolean getFinalGrade() {
		return finalGrade;
	}
	public void setFinalGrade(Boolean finalGrade) {
		this.finalGrade = finalGrade;
	}
	
	
	
	
	
}
