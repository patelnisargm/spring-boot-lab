package com.nmp.springmvcdemo.service;

import com.nmp.springmvcdemo.dto.Grade;

import java.util.List;

public interface IGradeService {
    Grade getGrade(int index);
    List<Grade> getAllGrades();
    void addGrade(Grade grade);
    void updateGrade(int index, Grade grade);
    Grade getGradeById(String id);
}
