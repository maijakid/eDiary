package com.iktpreobuka.eDiary.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.eDiary.entities.DepartmentEntity;
import com.iktpreobuka.eDiary.enums.EnumClass;
import com.iktpreobuka.eDiary.enums.EnumDepartment;
import com.iktpreobuka.eDiary.enums.SchoolClassDepartment;

public interface DepartmentRepository extends CrudRepository<DepartmentEntity, SchoolClassDepartment> {

	
	DepartmentEntity findBySchoolClassAndDepartment(EnumClass schoolClass, EnumDepartment department);
}
