package com.example.roster.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.roster.persistence.Student;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class StudentServiceTests {

    @Autowired
    private StudentService service;

    @Test
    public void testGetStudentById() {
        Student student = getInstance();

        student = service.saveStudent(student);

        assertNotNull(student.getId());

        Student result = service.getStudent(student.getId()).get();

        assertNotNull(result);
    }

    @Test
    public void testGetStudentByName() {
        Student student = getInstance();

        student = service.saveStudent(student);

        assertNotNull(student.getId());

        Student result = service.getStudent(student.getUsername()).get();

        assertNotNull(result);
    }

    @Test
    public void testSaveStudent() {
        //Create
        Student student = getInstance();

        student = service.saveStudent(student);

        assertNotNull(student.getId());

        //Edit
        student.setFirstname("Jane");

        Student result = service.saveStudent(student);

        assertEquals(result.getFirstname(), "Jane");
    }

    @Test
    public void testActivate() {
        Student student = getInstance();

        student.setActive(false);

        student = service.saveStudent(student);

        assertNotNull(student.getId());

        //Activate
        student.setActive(true);

        Student result = service.activateStudent(student.getId());

        assertTrue(result.isActive());
    }

    @Test
    public void testDeactivate() {
        Student student = getInstance();

        student = service.saveStudent(student);

        assertNotNull(student.getId());
        assertTrue(student.isActive());

        //Deactivate
        student.setActive(false);

        Student result = service.deactivateStudent(student.getId());

        assertFalse(result.isActive());
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