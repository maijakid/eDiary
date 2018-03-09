package com.iktpreobuka.eDnevnik.repositories;

import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.eDnevnik.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Long>, PagingAndSortingRepository<StudentEntity, Long>{

	public Set<StudentEntity> findAllByOrderBySurnameAscNameAsc();
//	
//	Sort sort = new Sort(new Sort.Order(Direction.ASC, "lastName"));

}
