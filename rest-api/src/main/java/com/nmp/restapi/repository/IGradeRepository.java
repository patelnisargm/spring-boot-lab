package com.nmp.restapi.repository;

import com.nmp.restapi.entity.Grade;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface IGradeRepository extends CrudRepository<Grade, Long> {
    Optional<Grade> findByStudentIdAndCourseId(Long studentID, Long courseID);

    @Transactional
    void deleteByStudentIdAndCourseId(Long studentID, Long courseID);

    List<Grade> findByStudentId(Long studentID);

    List<Grade> findByCourseId(Long courseID);
}