package com.example.mongodbtask.servicies;

import com.example.mongodbtask.dto.StudentRequestDTO;
import com.example.mongodbtask.dto.StudentResponseDTO;

import java.util.List;

public interface StudentsService {

    List<StudentResponseDTO> getAll();

    StudentResponseDTO getById(long id);

    StudentResponseDTO update(long id, StudentRequestDTO studentDTO);

    List<StudentResponseDTO> updateAll(List<StudentRequestDTO> toUpdate);

    List<StudentResponseDTO> createAll(List<StudentRequestDTO> toCreate);

    void delete(long id);

    void deleteAllById(List<Long> ids);
}
