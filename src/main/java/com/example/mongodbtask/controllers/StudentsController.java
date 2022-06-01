package com.example.mongodbtask.controllers;

import com.example.mongodbtask.servicies.StudentsService;
import com.example.mongodbtask.dto.StudentRequestDTO;
import com.example.mongodbtask.dto.StudentResponseDTO;
import com.example.mongodbtask.servicies.StudentsUtilsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@Slf4j
public class StudentsController {

    private final StudentsService studentsService;
    private final StudentsUtilsService studentsUtilsService;

    @GetMapping
    public List<StudentResponseDTO> getAll() {
        return studentsService.getAll();
    }

    @GetMapping(value = "/filtered")
    public List<StudentResponseDTO> getAllByLastName(@RequestParam(value = "lastName", defaultValue = "") String lastName, Pageable pageable) {
        return studentsUtilsService.getAllByLastName(lastName, pageable);
    }

    @PostMapping(value = "")
    public List<StudentResponseDTO> create(@RequestBody List<StudentRequestDTO> toCreate) {
        return studentsService.createAll(toCreate);
    }

    @PutMapping(value = "")
    public List<StudentResponseDTO> updateAll(@RequestBody List<StudentRequestDTO> toUpdate) {
        return studentsService.updateAll(toUpdate);
    }

    @DeleteMapping(value = "")
    public void deleteAll(@RequestBody List<Long> ids) {
        studentsService.deleteAllById(ids);
    }

    @GetMapping(value = "/{id}")
    public StudentResponseDTO getById(@PathVariable("id") long id) {
        return studentsService.getById(id);
    }

    @PutMapping(value = "/{id}")
    public StudentResponseDTO update(@PathVariable("id") long id, @RequestBody StudentRequestDTO requestDTO) {
        return studentsService.update(id, requestDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long id) {
        studentsService.delete(id);
    }

    @PutMapping(value = "/setLastNames")
    public List<StudentResponseDTO> setLastNames(@RequestParam("lastName") String lastName) {
        log.info("studentsService.setLastNameForStudents(lastName) = " + studentsUtilsService.setLastNameForStudents(lastName));
        return studentsService.getAll();
    }

    @DeleteMapping(value = "/deleteByLastName")
    public void deleteByLastName(@RequestParam("lastName") String lastName) {
        studentsUtilsService.deleteByLastName(lastName);
    }
}
