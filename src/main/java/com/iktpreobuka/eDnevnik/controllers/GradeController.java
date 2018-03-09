package com.iktpreobuka.eDnevnik.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDnevnik.entities.GradeEntity;
import com.iktpreobuka.eDnevnik.entities.StudentEntity;
import com.iktpreobuka.eDnevnik.entities.SubjectEntity;
import com.iktpreobuka.eDnevnik.repositories.GradeRepository;
import com.iktpreobuka.eDnevnik.repositories.StudentRepository;
import com.iktpreobuka.eDnevnik.repositories.SubjectRepository;

@RestController
@RequestMapping(path = "/api/v1/grade")
public class GradeController {

	
	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/{studentId}/{subjectId}")
	public ResponseEntity<?> addNewGrade (@RequestBody GradeEntity grade, @PathVariable Long studentId, @PathVariable Long subjectId) {
		StudentEntity se = studentRepository.findOne(studentId);
		SubjectEntity sub = subjectRepository.findOne(subjectId);	// dodati da li predmet pripada uceniku
		try {
			if ((se != null) && (sub != null)) {
				GradeEntity ge = new GradeEntity();
				ge.setGrade(grade.getGrade());
				ge.setComment(grade.getComment());
				ge.setDate(new Date());
				ge.setFinalGrade(false);
				ge.setStudent(se);
				ge.setSubject(sub);
				gradeRepository.save(ge);
				return new ResponseEntity<GradeEntity>(ge, HttpStatus.OK);
			} else if ((se == null) && (sub == null)) {
				return new ResponseEntity<RESTError>(new RESTError(1, "student nor subject found"), HttpStatus.NOT_FOUND);
			} else if (se == null) {
				return new ResponseEntity<RESTError>(new RESTError(1, "student not found"), HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<RESTError>(new RESTError(1, "subject not found"), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
}
