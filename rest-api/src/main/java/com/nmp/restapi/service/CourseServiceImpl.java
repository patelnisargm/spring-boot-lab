package com.nmp.restapi.service;

import com.nmp.restapi.entity.Course;
import com.nmp.restapi.entity.Student;
import com.nmp.restapi.exception.EntityNotFoundException;
import com.nmp.restapi.repository.ICourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements ICourseService {

    private ICourseRepository courseRepository;
    private IStudentService studentService;

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, Course.class));
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Student student = studentService.getStudent(studentId);
        Course course = getCourse(courseId);
        course.getStudents().add(student);
        return courseRepository.save(course);
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        return getCourse(id).getStudents();
    }

}
