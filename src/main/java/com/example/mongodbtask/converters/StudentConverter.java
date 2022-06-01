package com.example.mongodbtask.converters;

import com.example.mongodbtask.entities.Student;
import com.example.mongodbtask.dto.StudentRequestDTO;
import com.example.mongodbtask.dto.StudentResponseDTO;

public interface StudentConverter {

    Student toEntity(StudentRequestDTO dto);

    Student toEntity(long id, StudentRequestDTO dto);

    StudentResponseDTO toResponseDTO(Student entity);
}
