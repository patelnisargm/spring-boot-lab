package com.nmp.restapi;

import com.nmp.restapi.dto.Contact;
import com.nmp.restapi.entity.Course;
import com.nmp.restapi.entity.Student;
import com.nmp.restapi.service.IContactService;
import com.nmp.restapi.service.ICourseService;
import com.nmp.restapi.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;

@AllArgsConstructor
@SpringBootApplication
public class RestApiApplication implements CommandLineRunner {

    private IStudentService studentService;
    private ICourseService courseService;
    private IContactService contactService;

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        Student[] students = new Student[]{
                new Student("Harry Potter", LocalDate.parse(("1980-07-31"))),
                new Student("Ron Weasley", LocalDate.parse(("1980-03-01"))),
                new Student("Hermione Granger", LocalDate.parse(("1979-09-19"))),
                new Student("Neville Longbottom", LocalDate.parse(("1980-07-30")))
        };
        Arrays.stream(students).forEach(student -> studentService.saveStudent(student));

        Course[] courses = new Course[]{
                new Course("Charms", "CH104", "In this class, you will learn spells concerned with giving an object new and unexpected properties."),
                new Course("Defence Against the Dark Arts", "DADA", "In this class, you will learn defensive techniques against the dark arts."),
                new Course("Herbology", "HB311", "In this class, you will learn the study of magical plants and how to take care of, utilise and combat them."),
                new Course("History of Magic", "HIS393", "In this class, you will learn about significant events in wizarding history."),
                new Course("Potions", "POT102", "In this class, you will learn correct mixing and stirring of ingredients to create mixtures with magical effects."),
                new Course("Transfiguration", "TR442", "In this class, you will learn the art of changing the form or appearance of an object.")
        };
        Arrays.stream(courses).forEach(course -> courseService.saveCourse(course));

/*        contactService.getContacts().add(new Contact("1", "Jon Snow", "6123456432"));
        contactService.getContacts().add(new Contact("2", "Tyrion Lannister", "3125466472"));
        contactService.getContacts().add(new Contact("3", "The Hound", "5126476532"));*/
    }
}
