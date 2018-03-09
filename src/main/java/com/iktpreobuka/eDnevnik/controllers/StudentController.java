package com.iktpreobuka.eDnevnik.controllers;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDnevnik.entities.GradeEntity;
import com.iktpreobuka.eDnevnik.entities.ParentEntity;
import com.iktpreobuka.eDnevnik.entities.StudentEntity;
import com.iktpreobuka.eDnevnik.entities.SubjectEntity;
import com.iktpreobuka.eDnevnik.repositories.GradeRepository;
import com.iktpreobuka.eDnevnik.repositories.ParentRepository;
import com.iktpreobuka.eDnevnik.repositories.StudentRepository;
import com.iktpreobuka.eDnevnik.repositories.SubjectRepository;




@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	ParentRepository parentRepositoory;
	
	
	
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
	
//	get grades for subject
	@RequestMapping(method = RequestMethod.GET, value = "/{studentId}/{subjectId}")
	public ResponseEntity<?> getGradesForSubject(@PathVariable Long studentId, @PathVariable Long subjectId ) {
		StudentEntity se = studentRepository.findOne(studentId);
		SubjectEntity sub = subjectRepository.findOne(subjectId);
		try {
			if ((se != null) && (sub != null)) {
				for (GradeEntity ge : gradeRepository.findAll()) {
					if ((ge.getStudent()==se) && (ge.getSubject()==sub)) {
						return new ResponseEntity < Set <GradeEntity>>(sub.getGradesOnSubjects(), HttpStatus.OK);
					}
				} return new ResponseEntity<RESTError>(new RESTError(1, "student nor subject found"), HttpStatus.NOT_FOUND);//ispravi ostatak do kraja
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
	
//	get all by abc
	@RequestMapping(method = RequestMethod.GET, value = "/sort")
	public Iterable<StudentEntity> gettAllStudentsSort() {
		return studentRepository.findAllByOrderBySurnameAscNameAsc();
	}
	
// add parent
	@RequestMapping(method = RequestMethod.PUT, value = "/{studentId}/{parentId}")
	public ResponseEntity<?> addParentById ( @PathVariable Long studentId, @PathVariable Long parentId) {
		StudentEntity se = studentRepository.findOne(studentId);
		ParentEntity pe = parentRepositoory.findOne(parentId);
		try {
			if ((se != null) && (pe != null)){
				se.getParent().add(pe);
				pe.getChild().add(se);
				studentRepository.save(se);
				parentRepositoory.save(pe);
				return new ResponseEntity<StudentEntity>(se, HttpStatus.OK);
			} else if((se == null) && (pe == null)) {
				return new ResponseEntity<RESTError>(new RESTError(1, "student nor parent found"), HttpStatus.NOT_FOUND);
			} else if(se == null) {
				return new ResponseEntity<RESTError>(new RESTError(1, "student not found"), HttpStatus.NOT_FOUND);
			} else
			return new ResponseEntity<RESTError>(new RESTError(1, "parent not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
}
