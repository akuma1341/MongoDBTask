package com.example.mongodbtask.servicies.impl;

import com.example.mongodbtask.entities.Student;
import com.example.mongodbtask.repositories.StudentsRepository;
import com.example.mongodbtask.servicies.SequenceGeneratorService;
import com.example.mongodbtask.servicies.StudentsService;
import com.example.mongodbtask.converters.StudentConverter;
import com.example.mongodbtask.dto.StudentRequestDTO;
import com.example.mongodbtask.dto.StudentResponseDTO;
import com.example.mongodbtask.servicies.StudentsUtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements StudentsService, StudentsUtilsService {

    private static final String STUDENT_SEQUENCE_NAME = "students_sequence";

    private final StudentsRepository studentsRepository;
    private final StudentConverter studentConverter;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public List<StudentResponseDTO> getAll() {
        return studentsRepository.findAll().stream()
                .map(studentConverter::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> getAllByLastName(String lastName, Pageable pageable) {
        return studentsRepository.findByLastNameLike(lastName, pageable).stream()
                .map(studentConverter::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getById(long id) {
        if (studentsRepository.findById(id).isPresent()) {
            return studentConverter.toResponseDTO(studentsRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public StudentResponseDTO update(long id, StudentRequestDTO studentDTO) {
        Student updatedStudent = studentsRepository.save(studentConverter.toEntity(id, studentDTO));
        return studentConverter.toResponseDTO(updatedStudent);
    }

    @Override
    public List<StudentResponseDTO> updateAll(List<StudentRequestDTO> toUpdate) {
        List<Student> studentsToUpdate = toUpdate.stream()
                .map(studentConverter::toEntity)
                .collect(Collectors.toList());
        return studentsRepository.saveAll(studentsToUpdate).stream()
                .map(studentConverter::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> createAll(List<StudentRequestDTO> toCreate) {
        List<Student> studentsToCreate = toCreate.stream()
                .map(dto -> studentConverter.toEntity(generateId(), dto))
                .collect(Collectors.toList());
        return studentsRepository.saveAll(studentsToCreate).stream()
                .map(studentConverter::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {
        studentsRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Long> ids) {
        studentsRepository.deleteAllById(ids);
    }

    @Override
    public int setLastNameForStudents(String lastName) {
        List<Student> studentsToUpdate = studentsRepository.findAll();
        studentsToUpdate.forEach(student -> student.setLastName(lastName));
        return studentsRepository.saveAll(studentsToUpdate).size();
    }

    @Override
    public void deleteByLastName(String lastName) {
        List<Student> studentsToDelete = studentsRepository.findByLastNameLike(lastName, Pageable.unpaged());
        studentsRepository.deleteAll(studentsToDelete);
    }

    private long generateId() {
        return sequenceGeneratorService.generateSequence(STUDENT_SEQUENCE_NAME);
    }
}
