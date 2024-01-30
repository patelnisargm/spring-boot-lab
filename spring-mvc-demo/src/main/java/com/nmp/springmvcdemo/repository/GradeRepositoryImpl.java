package com.nmp.springmvcdemo.repository;

import com.nmp.springmvcdemo.dto.Grade;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class GradeRepositoryImpl implements IGradeRepository {
    private final List<Grade> studentGrades = new ArrayList<>();
    @Override
    public Grade getGrade(int index) {
        return studentGrades.get(index);
    }

    @Override
    public List<Grade> getAllGrades() {
        return studentGrades;
    }

    @Override
    public void addGrade(Grade grade) {
        studentGrades.add(grade);
    }

    @Override
    public void updateGrade(int index, Grade grade) {
        studentGrades.set(index, grade);
    }
}
