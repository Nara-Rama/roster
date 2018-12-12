package com.example.roster.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.roster.persistence.StudentRepository;
import com.example.roster.persistence.Student;
import com.example.roster.service.StudentService;
import com.example.roster.persistence.ValidationException;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudent(String username) {
        return studentRepository.findByUsername(username);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student activateStudent(Long id) {
        return activate(id, true);
    }

    public Student deactivateStudent(Long id) {
        return activate(id, false);
    }

    private Student activate(Long id, boolean active) {
        Optional<Student> student = getStudent(id);

        if (!student.isPresent())
            throw new ValidationException("Invalid student id: " + id);

        student.get().setActive(active);

        return saveStudent(student.get());
    }
}