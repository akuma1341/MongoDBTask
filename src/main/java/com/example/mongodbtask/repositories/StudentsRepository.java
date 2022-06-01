package com.example.mongodbtask.repositories;

import com.example.mongodbtask.entities.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentsRepository extends MongoRepository<Student, Long> {

    List<Student> findByLastNameLike(String lastName, Pageable pageable);
}
