package com.nmp.springmvcdemo.service;

import com.nmp.springmvcdemo.dto.Grade;
import com.nmp.springmvcdemo.repository.GradeRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class GradeServiceImplTest {
    @Mock
    GradeRepositoryImpl gradeRepository;
    @InjectMocks
    GradeServiceImpl gradeService;

    @Test
    void addGrade() {
        Grade grade = new Grade("john", "C", "Java");
        when(gradeRepository.getAllGrades()).thenReturn(List.of(grade));
        Grade newGrade = new Grade("Emma", "A", "C#");
        gradeService.addGrade(newGrade);
        verify(gradeRepository, times(1)).addGrade(newGrade);
    }

    @Test
    void updateGrade() {
        Grade grade = new Grade("john", "C", "Java");
        when(gradeRepository.getAllGrades()).thenReturn(List.of(grade));
        grade.setSubject("C#");
        gradeService.addGrade(grade);
        verify(gradeRepository, times(1)).updateGrade(0 ,grade);
    }

    @Test
    void getGradeById() {
        Grade grade = new Grade("john", "C", "Java");
        when(gradeRepository.getAllGrades()).thenReturn(List.of(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);
        Grade gradeResult = gradeService.getGradeById(grade.getId());
        Assertions.assertEquals(grade, gradeResult);
    }
}