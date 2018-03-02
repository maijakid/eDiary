package com.iktpreobuka.eDiary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDiary.entities.StudentEntity;
import com.iktpreobuka.eDiary.repositories.StudentRepository;




@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

	
	@Autowired
	StudentRepository studentRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewStudent(@RequestBody StudentEntity student /*@RequestParam String name, @RequestParam String surname*/) {
		StudentEntity se = new StudentEntity();
		se.setName(student.getName());
		se.setSurname(student.getSurname());
		studentRepository.save(se);
		return new ResponseEntity<StudentEntity>(se, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<StudentEntity> gettAllStudents() {
		return studentRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		StudentEntity se = studentRepository.findOne(id);
		try {
			if (se != null) {		// ako je korisnik pronadjen vratiti 200
				return new ResponseEntity<StudentEntity>(se, HttpStatus.OK);
			}		// ako korisnik nije pronadjen vratiti 404
			return new ResponseEntity<RESTError>(new RESTError(1, "student not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		StudentEntity se = studentRepository.findOne(id);
		try {
			if (se != null) {
				studentRepository.delete(se);
				return new ResponseEntity<StudentEntity>(se, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "student not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modifyById (@RequestBody StudentEntity student, @PathVariable Long id) {
		StudentEntity se = studentRepository.findOne(id);
		try {
			if (se != null) {
				if (student.getName()!=null) {
					se.setName(student.getName());
				}
				if (student.getSurname()!=null) {
					se.setSurname(student.getSurname());
				}
//				if (user.getEmail()!=null) {
//					se.setEmail(user.getEmail());
//				}
//				if (user.getPassword()!=null) {
//					se.setPassword(user.getPassword());
//				}
				studentRepository.save(se);
				return new ResponseEntity<StudentEntity>(se, HttpStatus.OK);
			} 
			return new ResponseEntity<RESTError>(new RESTError(1, "student not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
//	get students by department
	
	
	
}
