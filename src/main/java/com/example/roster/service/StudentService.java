package com.example.roster.service;

import java.util.Optional;

import com.example.roster.persistence.Student;

/*
 * Student service
 */
public interface StudentService {
    public Optional<Student> getStudent(Long id);

    public Optional<Student> getStudent(String username);

    public Student saveStudent(Student student);

    public Student activateStudent(Long id);

    public Student deactivateStudent(Long id);
}