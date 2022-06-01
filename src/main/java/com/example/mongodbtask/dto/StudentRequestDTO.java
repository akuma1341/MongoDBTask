package com.example.mongodbtask.dto;

import com.example.mongodbtask.entities.ExamMark;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class StudentRequestDTO {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
    private final List<ExamMark> examMarks;
}
