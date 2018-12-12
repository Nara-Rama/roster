package com.example.roster.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository repository;

    @Test
    public void testFindById() {
        Student student = getInstance();

        student = repository.save(student);

        assertThat(repository.findById(student.getId())).hasValue(student);
    }

    @Test
    public void testFindByName() {
        Student student = getInstance();

        student = repository.save(student);

        assertThat(repository.findByUsername(student.getUsername())).hasValue(student);
    }

    private Student getInstance() {
        Student student = new Student();

        student.setUsername("username1");
        student.setFirstname("John");
        student.setLastname("Doe");
        student.setEmail("user@google.com");
        student.setActive(true);

        return student;
    }
}