package com.example.mongodbtask.converters.impl;

import com.example.mongodbtask.entities.Student;
import com.example.mongodbtask.converters.StudentConverter;
import com.example.mongodbtask.dto.StudentRequestDTO;
import com.example.mongodbtask.dto.StudentResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentConverterImpl implements StudentConverter {

    @Override
    public Student toEntity(StudentRequestDTO dto) {
        return convertToEntity(dto.getId(), dto);
    }

    @Override
    public Student toEntity(long id, StudentRequestDTO dto) {
        return convertToEntity(id, dto);
    }

    @Override
    public StudentResponseDTO toResponseDTO(Student entity) {
        return StudentResponseDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .examMarks(entity.getExamMarks())
                .build();
    }

    private Student convertToEntity(long id, StudentRequestDTO dto) {
        return Student.builder()
                .id(id)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthDate(dto.getBirthDate())
                .examMarks(dto.getExamMarks())
                .build();
    }
}
