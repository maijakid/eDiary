package com.iktpreobuka.eDnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.iktpreobuka.eDnevnik.entities.ParentEntity;
import com.iktpreobuka.eDnevnik.entities.PersonEntity;
import com.iktpreobuka.eDnevnik.entities.StudentEntity;
import com.iktpreobuka.eDnevnik.entities.TeacherEntity;
import com.iktpreobuka.eDnevnik.entities.dto.PersonDTO;
import com.iktpreobuka.eDnevnik.enums.EnumPerson;
import com.iktpreobuka.eDnevnik.repositories.ParentRepository;
import com.iktpreobuka.eDnevnik.repositories.StudentRepository;
import com.iktpreobuka.eDnevnik.repositories.TeacherRepository;



@RestController
@RequestMapping(path = "/api/v1/person")
public class PersonController {

	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	ParentRepository parentRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewSPerson(@RequestParam EnumPerson p, @RequestBody PersonDTO person) {
//		ObjectMapper om = new ObjectMapper();
//		om.configure(SerializationFeature.INDENT_OUTPUT, true);
		try {
			switch (p) {
				case U: StudentEntity se = new StudentEntity();
//						se = om.readValue(person, StudentEntity.class);
						se.setName(person.getName());
						se.setSurname(person.getSurname());
						studentRepository.save(se);
						return new ResponseEntity<StudentEntity>(se, HttpStatus.OK);
						
				case N: TeacherEntity te = new TeacherEntity();
//						te = (TeacherEntity)person;
						te.setName(person.getName());
						te.setSurname(person.getSurname());
						teacherRepository.save(te);
						return new ResponseEntity<TeacherEntity>(te, HttpStatus.OK);
						
				case R: ParentEntity pe = new ParentEntity();
//						pe = (ParentEntity)person;
						pe.setName(person.getName());
						pe.setSurname(person.getSurname());
						parentRepository.save(pe);
						return new ResponseEntity<ParentEntity>(pe, HttpStatus.OK);
						
	//			case A: admin
				default: return new ResponseEntity<RESTError>(new RESTError(1, "not acceptable person type"), HttpStatus.NOT_ACCEPTABLE);
				}
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	
}
