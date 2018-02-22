package com.iktpreobuka.eDiary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDiary.entities.StudentEntity;
import com.iktpreobuka.eDiary.repositories.StudentRepository;


@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

	
	@Autowired
	StudentRepository studentRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public StudentEntity addNewStudent(@RequestBody StudentEntity student /*@RequestParam String name, @RequestParam String surname*/) {
	StudentEntity se = new StudentEntity();
	se.setName(student.getName());
	se.setSurname(student.getSurname());
	studentRepository.save(se);
	return se;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<StudentEntity> gettAllStudents() {
		return studentRepository.findAll();
	}
	
	
	
}
