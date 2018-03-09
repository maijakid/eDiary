package com.iktpreobuka.eDnevnik.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDnevnik.entities.SubjectEntity;
import com.iktpreobuka.eDnevnik.entities.TeacherEntity;
import com.iktpreobuka.eDnevnik.repositories.SubjectRepository;
import com.iktpreobuka.eDnevnik.repositories.TeacherRepository;

@RestController
@RequestMapping(path = "/api/v1/subject")
public class SubjectController {

	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public SubjectEntity addNewSubject(@RequestBody SubjectEntity subject /*@RequestParam String name, @RequestParam String surname*/) {
	SubjectEntity se = new SubjectEntity();
	se.setSubject(subject.getSubject());
	se.setFund(subject.getFund());
	subjectRepository.save(se);
	return se;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<SubjectEntity> gettAllSubjects() {
		return subjectRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		SubjectEntity se = subjectRepository.findOne(id);
		try {
			if (se != null) {		// ako je korisnik pronadjen vratiti 200
				return new ResponseEntity<SubjectEntity>(se, HttpStatus.OK);
			}		// ako korisnik nije pronadjen vratiti 404
			return new ResponseEntity<RESTError>(new RESTError(1, "subject not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		SubjectEntity se = subjectRepository.findOne(id);
		try {
			if (se != null) {
				subjectRepository.delete(se);
				return new ResponseEntity<SubjectEntity>(se, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "subject not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modifyById (@RequestBody SubjectEntity subject, @PathVariable Long id) {
		SubjectEntity se = subjectRepository.findOne(id);
		try {
			if (se != null) {
				if (subject.getSubject()!=null) {
					se.setSubject(subject.getSubject());
				}
				if (subject.getFund()!=null) {
					se.setFund(subject.getFund());
				}
//				if (user.getEmail()!=null) {
//					se.setEmail(user.getEmail());
//				}
//				if (user.getPassword()!=null) {
//					se.setPassword(user.getPassword());
//				}
				subjectRepository.save(se);
				return new ResponseEntity<SubjectEntity>(se, HttpStatus.OK);
			} 
			return new ResponseEntity<RESTError>(new RESTError(1, "subject not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{subjectId}/{teacherId}")
	public  ResponseEntity<?> addTeacherById (@PathVariable Long teacherId, @PathVariable Long subjectId) {
		TeacherEntity te = teacherRepository.findOne(teacherId);
		SubjectEntity se = subjectRepository.findOne(subjectId);
		try {
			if ((te != null) && (se != null)) {
				te.getTeacherOnSubjects().add(se);
				se.getTeachersOnSubject().add(te);
				teacherRepository.save(te);
				subjectRepository.save(se);
				return new ResponseEntity<SubjectEntity>(se, HttpStatus.OK);
			} else if ((te == null) && (se == null))  {
				return new ResponseEntity<RESTError>(new RESTError(1, "subject and teacher not found"), HttpStatus.NOT_FOUND);
			} else if (te == null)  {
				return new ResponseEntity<RESTError>(new RESTError(1, "teacher not found"), HttpStatus.NOT_FOUND);
			} /*else if (se == null)  {*/
				return new ResponseEntity<RESTError>(new RESTError(1, "subject not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}	
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{subjectId}/{teacherId}")
	public  ResponseEntity<?> deleteTeacherById (@PathVariable Long teacherId, @PathVariable Long subjectId) {
		TeacherEntity te = teacherRepository.findOne(teacherId);
		SubjectEntity se = subjectRepository.findOne(subjectId);
		try {
			if ((te != null) && (se != null)) {
				Set<TeacherEntity> teachers = se.getTeachersOnSubject();
				if (teachers.contains(te)) {
					te.getTeacherOnSubjects().remove(se);
					se.getTeachersOnSubject().remove(te);
					teacherRepository.save(te);
					subjectRepository.save(se);
					return new ResponseEntity<SubjectEntity>(se, HttpStatus.OK);
				} else 
					return new ResponseEntity<RESTError>(new RESTError(1, "teacher not found on subject"), HttpStatus.NOT_FOUND);
			} else if ((te == null) && (se == null))  {
				return new ResponseEntity<RESTError>(new RESTError(1, "subject and teacher not found"), HttpStatus.NOT_FOUND);
			} else if (te == null)  {
				return new ResponseEntity<RESTError>(new RESTError(1, "teacher not found"), HttpStatus.NOT_FOUND);
			} /*else if (se == null)  {*/
				return new ResponseEntity<RESTError>(new RESTError(1, "subject not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}	
	}
	
	
	
}
