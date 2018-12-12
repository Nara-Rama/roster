package com.example.roster.web;

import org.junit.Test;
import static org.junit.Assert.fail;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.roster.persistence.Student;

public class StudentControllerTests extends AbstractControllerTests {
    @Test
    public void testGet() {
        try {
            this.mockMvc.perform(mockGetRequest("/students/1"))
                    .andDo(print()).andExpect(status().isOk());
        } catch(Exception ex) {
            fail("Get failed with error: " + ex.getMessage());
        }
    }

    @Test
    public void testCreate() {
        try {
            Student student = getInstance();

            this.mockMvc.perform(mockPostRequest("/students/", student))
                    .andDo(print()).andExpect(status().isOk());
        } catch (Exception ex) {
            fail("Create failed with error: " + ex.getMessage());
        }
    }

    //TODO: Add the tests for remaining controller methods

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