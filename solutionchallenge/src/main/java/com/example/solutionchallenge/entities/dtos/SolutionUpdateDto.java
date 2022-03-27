package com.example.solutionchallenge.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionUpdateDto {
    private int id;
    private int homeworkId;
    private int studentId;
    private Date date;
    private String description;

}
