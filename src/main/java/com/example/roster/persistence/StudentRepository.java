package com.example.roster.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/*
 * Spring data handles the implementation
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findByUsername(String username);
}