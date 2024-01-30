package com.nmp.restapi.service;

import com.nmp.restapi.entity.Course;
import com.nmp.restapi.entity.Student;
import com.nmp.restapi.exception.EntityNotFoundException;
import com.nmp.restapi.repository.IStudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements IStudentService {

    private IStudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Student.class));
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Set<Course> getEnrolledCourses(Long id) {
        return getStudent(id).getCourses();
    }

}