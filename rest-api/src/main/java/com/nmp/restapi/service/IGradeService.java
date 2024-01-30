package com.nmp.restapi.service;

import com.nmp.restapi.entity.Grade;

import java.util.List;

public interface IGradeService {
    Grade getGrade(Long studentId, Long courseId);

    Grade saveGrade(Grade grade, Long studentId, Long courseId);

    Grade updateGrade(String score, Long studentId, Long courseId);

    void deleteGrade(Long studentId, Long courseId);

    List<Grade> getStudentGrades(Long studentId);

    List<Grade> getCourseGrades(Long courseId);

    List<Grade> getAllGrades();
}
