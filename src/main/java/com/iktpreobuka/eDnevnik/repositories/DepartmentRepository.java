package com.iktpreobuka.eDnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.eDnevnik.entities.DepartmentEntity;
import com.iktpreobuka.eDnevnik.enums.EnumClass;
import com.iktpreobuka.eDnevnik.enums.EnumDepartment;
import com.iktpreobuka.eDnevnik.enums.SchoolClassDepartment;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, SchoolClassDepartment> {

	
	DepartmentEntity findBySchoolClassAndDepartment(EnumClass schoolClass, EnumDepartment department);
}
