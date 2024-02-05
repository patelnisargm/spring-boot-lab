package com.nmp.springmvcdemo.service;

import com.nmp.springmvcdemo.Constants;
import com.nmp.springmvcdemo.dto.Grade;
import com.nmp.springmvcdemo.repository.IGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Service
public class GradeServiceImpl implements IGradeService {
    @Autowired
    private IGradeRepository repository;

    public GradeServiceImpl(IGradeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Grade getGrade(int index) {
        return repository.getGrade(index);
    }

    @Override
    public List<Grade> getAllGrades() {
        return repository.getAllGrades();
    }

    @Override
    public void addGrade(Grade grade) {
        int index = getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            repository.addGrade(grade);
        } else {
            updateGrade(index, grade);
        }
    }

    @Override
    public void updateGrade(int index, Grade grade) {
        repository.updateGrade(index, grade);
    }

    @Override
    public Grade getGradeById(String id) {
        int index = getGradeIndex(id);
        return (index == Constants.NOT_FOUND ? new Grade() : getGrade(index));
    }

    private int getGradeIndex(String id) {
        List<Grade> grades = getAllGrades();
        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }
}
