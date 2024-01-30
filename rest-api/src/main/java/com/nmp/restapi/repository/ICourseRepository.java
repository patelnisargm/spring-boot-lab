package com.nmp.restapi.repository;

import com.nmp.restapi.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseRepository extends CrudRepository<Course, Long> {
}