package com.example.mongodbtask.dto;

import com.example.mongodbtask.entities.ExamMark;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class StudentResponseDTO {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
    private final List<ExamMark> examMarks;
}
