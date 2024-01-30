package com.nmp.restapi.service;

import com.nmp.restapi.entity.Course;
import com.nmp.restapi.entity.Student;

import java.util.List;
import java.util.Set;


public interface IStudentService {
    Student getStudent(Long id);

    Student saveStudent(Student student);

    void deleteStudent(Long id);

    List<Student> getStudents();

    Set<Course> getEnrolledCourses(Long id);
}