package com.iktpreobuka.eDiary.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDiary.entities.DepartmentEntity;
import com.iktpreobuka.eDiary.entities.StudentEntity;
import com.iktpreobuka.eDiary.entities.DepartmentEntity;
import com.iktpreobuka.eDiary.enums.EnumClass;
import com.iktpreobuka.eDiary.enums.EnumDepartment;
import com.iktpreobuka.eDiary.repositories.DepartmentRepository;
import com.iktpreobuka.eDiary.repositories.StudentRepository;

import org.apache.commons.lang3.EnumUtils;

@RestController
@RequestMapping(path = "/api/v1/department")
public class DepartmentController {

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	StudentRepository studentRepository;
	

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewDepartment(@RequestBody DepartmentEntity department) {//ne radi prvi deo jer z apogresne enume odma izbaci bad req
		Boolean depValid = EnumUtils.isValidEnum(EnumDepartment.class, department.getDepartment().toString());	//radi i za razred koji ne postoji!
		Boolean classValid = EnumUtils.isValidEnum(EnumClass.class, department.getSchoolClass().toString());
		if (!depValid && !classValid) {
			return new ResponseEntity<RESTError>(new RESTError(1, "department nor class valid"), HttpStatus.BAD_REQUEST);
		} else if (!depValid) {
			return new ResponseEntity<RESTError>(new RESTError(1, "department not valid"), HttpStatus.BAD_REQUEST);
		} else if (!classValid) {
			return new ResponseEntity<RESTError>(new RESTError(1, "class not valid"), HttpStatus.BAD_REQUEST);
		} else {
			DepartmentEntity de = departmentRepository.findBySchoolClassAndDepartment(department.getSchoolClass(), department.getDepartment());
			try {
				if (de != null) {
					return new ResponseEntity<RESTError>(new RESTError(1, "department in class already exists"), HttpStatus.ALREADY_REPORTED);
				} 
				{
					de = new DepartmentEntity();
					de.setSchoolClass(department.getSchoolClass());
					de.setDepartment(department.getDepartment());
					departmentRepository.save(de);
					return new ResponseEntity<DepartmentEntity>(de, HttpStatus.OK);	
				}
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<DepartmentEntity> getAll(){
			return departmentRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{classId}/{departmentId}")
	public ResponseEntity<?> getById(@PathVariable EnumClass classId, @PathVariable EnumDepartment departmentId) {
		DepartmentEntity de = departmentRepository.findBySchoolClassAndDepartment(classId, departmentId);
		try {
			if (de != null) {
				return new ResponseEntity<DepartmentEntity>(de, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "department not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{classId}/{departmentId}")
	public ResponseEntity<?> deleteById(@PathVariable EnumClass classId, @PathVariable EnumDepartment departmentId) {
		DepartmentEntity de = departmentRepository.findBySchoolClassAndDepartment(classId, departmentId);
		try {
			if (de != null) {
				departmentRepository.delete(de);
				return new ResponseEntity<DepartmentEntity>(de, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "department not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{classId}/{departmentId}/{studentId}")
	public ResponseEntity<?> addStudentToDepartment(@PathVariable EnumClass classId, @PathVariable EnumDepartment departmentId, @PathVariable Long studentId) {
		DepartmentEntity de = departmentRepository.findBySchoolClassAndDepartment(classId, departmentId);
		StudentEntity se = studentRepository.findOne(studentId);
		try {
				if ((de != null) && (se != null)) {
					if (se.getSchoolClassDepartment() == null) {
						de.getStudentsInDepatment().add(se);
						se.setSchoolClassDepartment(de);
						departmentRepository.save(de);
						studentRepository.save(se);
						return new ResponseEntity<DepartmentEntity>(de, HttpStatus.OK);
					} else {
						return new ResponseEntity<RESTError>(new RESTError(1, "student already have department assigned"), HttpStatus.CONFLICT);
					}
				} else if ((de == null) && (se == null)) {
					return new ResponseEntity<RESTError>(new RESTError(1, "department nor student found"), HttpStatus.NOT_FOUND);
				} else if (de == null) {
					return new ResponseEntity<RESTError>(new RESTError(1, "department not found"), HttpStatus.NOT_FOUND);
				} else {
					return new ResponseEntity<RESTError>(new RESTError(1, "student not found"), HttpStatus.NOT_FOUND);
				}
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	
	
}
