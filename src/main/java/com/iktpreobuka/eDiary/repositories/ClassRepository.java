package com.iktpreobuka.eDiary.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.eDiary.entities.ClassEntity;
import com.iktpreobuka.eDiary.enums.EnumClass;

public interface ClassRepository extends CrudRepository<ClassEntity, EnumClass>{

}
