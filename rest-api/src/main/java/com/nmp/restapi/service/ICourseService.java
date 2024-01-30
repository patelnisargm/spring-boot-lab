package com.nmp.restapi.service;

import com.nmp.restapi.entity.Course;
import com.nmp.restapi.entity.Student;

import java.util.List;
import java.util.Set;

public interface ICourseService {
    Course getCourse(Long id);

    Course saveCourse(Course course);

    void deleteCourse(Long id);

    List<Course> getCourses();

    Course addStudentToCourse(Long studentId, Long courseId);

    Set<Student> getEnrolledStudents(Long id);
}