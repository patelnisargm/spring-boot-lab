package com.nmp.springmvcdemo.repository;

import com.nmp.springmvcdemo.dto.Grade;

import java.util.List;

public interface IGradeRepository {
    public Grade getGrade(int index);
    public List<Grade> getAllGrades();
    public void addGrade(Grade grade);
    public void updateGrade(int index, Grade grade);
}
