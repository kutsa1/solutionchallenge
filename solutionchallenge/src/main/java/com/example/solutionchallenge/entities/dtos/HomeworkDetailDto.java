package com.example.solutionchallenge.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeworkDetailDto {
    private int id;
    private String description;
    private String studentName;
    private String studentLastName;
    private Date date;
    private int homeworkId;
}
