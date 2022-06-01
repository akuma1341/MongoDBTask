package com.example.mongodbtask.servicies;

import com.example.mongodbtask.dto.StudentResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentsUtilsService {

    List<StudentResponseDTO> getAllByLastName(String lastName, Pageable pageable);

    int setLastNameForStudents(String lastName);

    void deleteByLastName(String lastName);
}
