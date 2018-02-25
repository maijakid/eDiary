package com.iktpreobuka.eDiary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDiary.entities.SubjectEntity;
import com.iktpreobuka.eDiary.repositories.SubjectRepository;

@RestController
@RequestMapping(path = "/api/v1/subject")
public class SubjectController {

	@Autowired
	SubjectRepository subjectRepository;
	
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
	public ResponseEntity<?> getById(@PathVariable Integer id) {
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
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
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
	public ResponseEntity<?> modifyById (@RequestBody SubjectEntity subject, @PathVariable Integer id) {
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
}
