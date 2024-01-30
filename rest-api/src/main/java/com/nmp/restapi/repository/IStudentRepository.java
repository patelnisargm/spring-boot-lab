package com.nmp.restapi.repository;

import com.nmp.restapi.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface IStudentRepository extends CrudRepository<Student, Long> {
}