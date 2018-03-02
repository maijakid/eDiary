package com.iktpreobuka.eDiary.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDiary.entities.SubjectEntity;
import com.iktpreobuka.eDiary.entities.TeacherEntity;
import com.iktpreobuka.eDiary.repositories.SubjectRepository;
import com.iktpreobuka.eDiary.repositories.TeacherRepository;

@RestController
@RequestMapping(path = "/api/v1/teacher")
public class TeacherController {

	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public TeacherEntity addNewStudent(@RequestBody TeacherEntity teacher /*@RequestParam String name, @RequestParam String surname*/) {
	TeacherEntity te = new TeacherEntity();
	te.setName(teacher.getName());
	te.setSurname(teacher.getSurname());
	teacherRepository.save(te);
	return te;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<TeacherEntity> gettAllTeachers() {
		return teacherRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		TeacherEntity te = teacherRepository.findOne(id);
		try {
			if (te != null) {		// ako je korisnik pronadjen vratiti 200
				return new ResponseEntity<TeacherEntity>(te, HttpStatus.OK);
			}		// ako korisnik nije pronadjen vratiti 404
			return new ResponseEntity<RESTError>(new RESTError(1, "teacher not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		TeacherEntity te = teacherRepository.findOne(id);
		try {
			if (te != null) {
				teacherRepository.delete(te);
				return new ResponseEntity<TeacherEntity>(te, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "teacher not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modifyById (@RequestBody TeacherEntity teacher, @PathVariable Long id) {
		TeacherEntity te = teacherRepository.findOne(id);
		try {
			if (te != null) {
				if (teacher.getName()!=null) {
					te.setName(teacher.getName());
				}
				if (teacher.getSurname()!=null) {
					te.setSurname(teacher.getSurname());
				}
//				if (user.getEmail()!=null) {
//					se.setEmail(user.getEmail());
//				}
//				if (user.getPassword()!=null) {
//					se.setPassword(user.getPassword());
//				}
				teacherRepository.save(te);
				return new ResponseEntity<TeacherEntity>(te, HttpStatus.OK);
			} 
			return new ResponseEntity<RESTError>(new RESTError(1, "teacher not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
//	@RequestMapping(method = RequestMethod.PUT, value = "/{teacherId}/{subjectId}")
//	public  ResponseEntity<?> addSubjectById (@PathVariable Long teacherId, @PathVariable Integer subjectId) {
//		TeacherEntity te = teacherRepository.findOne(teacherId);
//		SubjectEntity se = subjectRepository.findOne(subjectId);
//		try {
//			if ((te != null) && (se != null)) {
//				List<SubjectEntity> subject = new ArrayList();
//				subject.add(se);
//				te.setTeacherOnSubjects(subject);
//			}
//		}
//	}
	
	
	
}
