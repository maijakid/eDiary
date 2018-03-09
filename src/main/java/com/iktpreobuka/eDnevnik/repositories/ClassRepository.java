package com.iktpreobuka.eDnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.eDnevnik.entities.ClassEntity;
import com.iktpreobuka.eDnevnik.enums.EnumClass;

public interface ClassRepository extends CrudRepository<ClassEntity, EnumClass>{

}
