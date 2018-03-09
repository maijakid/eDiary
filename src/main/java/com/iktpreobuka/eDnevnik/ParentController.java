package com.iktpreobuka.eDnevnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDnevnik.controllers.RESTError;
import com.iktpreobuka.eDnevnik.entities.ParentEntity;
import com.iktpreobuka.eDnevnik.repositories.ParentRepository;


@RestController
@RequestMapping(path = "/api/v1/parent")
public class ParentController {

	@Autowired
	ParentRepository parentRepository;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addNewParent(@RequestBody ParentEntity parent) {
		ParentEntity pe = new ParentEntity();
		pe.setName(parent.getName());
		pe.setSurname(parent.getSurname());
		parentRepository.save(pe);
		return new ResponseEntity<ParentEntity>(pe, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<ParentEntity> gettAllParents() {
		return parentRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		ParentEntity pe = parentRepository.findOne(id);
		try {
			if (pe != null) {		// ako je korisnik pronadjen vratiti 200
				return new ResponseEntity<ParentEntity>(pe, HttpStatus.OK);
			}		// ako korisnik nije pronadjen vratiti 404
			return new ResponseEntity<RESTError>(new RESTError(1, "parent not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		ParentEntity se = parentRepository.findOne(id);
		try {
			if (se != null) {
				parentRepository.delete(se);
				return new ResponseEntity<ParentEntity>(se, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "parent not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> modifyById (@RequestBody ParentEntity parent, @PathVariable Long id) {
		ParentEntity pe = parentRepository.findOne(id);
		try {
			if (pe != null) {
				if (parent.getName()!=null) {
					pe.setName(parent.getName());
				}
				if (parent.getSurname()!=null) {
					pe.setSurname(parent.getSurname());
				}
//				if (user.getEmail()!=null) {
//					se.setEmail(user.getEmail());
//				}
//				if (user.getPassword()!=null) {
//					se.setPassword(user.getPassword());
//				}
				parentRepository.save(pe);
				return new ResponseEntity<ParentEntity>(pe, HttpStatus.OK);
			} 
			return new ResponseEntity<RESTError>(new RESTError(1, "pparent not found"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
//	get children
	
	
	
	
	
	
	
	
}
