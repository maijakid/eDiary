package com.iktpreobuka.eDnevnik.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDnevnik.entities.ClassEntity;
import com.iktpreobuka.eDnevnik.entities.SubjectEntity;
import com.iktpreobuka.eDnevnik.enums.EnumClass;
import com.iktpreobuka.eDnevnik.repositories.ClassRepository;
import com.iktpreobuka.eDnevnik.repositories.SubjectRepository;


@RestController
@RequestMapping(path = "/api/v1/class")
public class ClassController {

	
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewClass(@RequestBody ClassEntity schoolClass) {
		ClassEntity ce = classRepository.findOne(schoolClass.getSchoolClass());
		try {
			if (ce != null) {
				return new ResponseEntity<RESTError>(new RESTError(1, "this class already exists"), HttpStatus.ALREADY_REPORTED);
			} 
			
			else {
				ce = schoolClass;
				classRepository.save(ce);
				return new ResponseEntity<ClassEntity>(ce, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<ClassEntity> getAll(){
			return classRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable EnumClass id) {
		ClassEntity ce = classRepository.findOne(id);
		try {
			if (ce != null) {
				return new ResponseEntity<ClassEntity>(ce, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "class not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable EnumClass id) {
		ClassEntity ce = classRepository.findOne(id);
		try {
			if (ce != null) {
				classRepository.delete(ce);
				return new ResponseEntity<ClassEntity>(ce, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "class not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{classId}/{subjectId}")
	public ResponseEntity<?> addSubjectToClass (@PathVariable EnumClass classId, @PathVariable Long subjectId) {
		ClassEntity ce = classRepository.findOne(classId);
		SubjectEntity se = subjectRepository.findOne(subjectId);
		try {
			if ((ce != null) && (se != null)) {	//ne prijavljuje vec postojece, ako je predmet vec dodeljen drugom razredu premestice ga!!!
				ce.getSubjectsInClass().add(se);
				se.setSchoolClass(ce);
				classRepository.save(ce);
				subjectRepository.save(se);
				return new ResponseEntity<ClassEntity>(ce, HttpStatus.OK);
			} else if ((ce == null) && (se == null))  {
				return new ResponseEntity<RESTError>(new RESTError(1, "subject and class not found"), HttpStatus.NOT_FOUND);
			} else if (ce == null)  {
				return new ResponseEntity<RESTError>(new RESTError(1, "class not found"), HttpStatus.NOT_FOUND);
			} /*else if (ce == null)  {*/
				return new ResponseEntity<RESTError>(new RESTError(1, "subject not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{classId}/{subjectId}")
	public ResponseEntity<?> deleteSubjectFromClass (@PathVariable EnumClass classId, @PathVariable Long subjectId) {
		ClassEntity ce = classRepository.findOne(classId);
		SubjectEntity se = subjectRepository.findOne(subjectId);
		try {
			if ((ce != null) && (se != null)) {
				Set<SubjectEntity> subjects = ce.getSubjectsInClass();
				if (subjects.contains(se)) {
					ce.getSubjectsInClass().remove(se);
					se.setSchoolClass(null);
					classRepository.save(ce);
					subjectRepository.save(se);
					return new ResponseEntity<ClassEntity>(ce, HttpStatus.OK);
				} else 
					return new ResponseEntity<RESTError>(new RESTError(1, "subject not found in class"), HttpStatus.NOT_FOUND);
			} else if ((ce == null) && (se == null))  {
				return new ResponseEntity<RESTError>(new RESTError(1, "subject and class not found"), HttpStatus.NOT_FOUND);
			} else if (ce == null)  {
				return new ResponseEntity<RESTError>(new RESTError(1, "class not found"), HttpStatus.NOT_FOUND);
			} /*else if (ce == null)  {*/
				return new ResponseEntity<RESTError>(new RESTError(1, "subject not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
}
