package com.example.solutionchallenge.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailDto {
    private int id;
    private int studentId;
    private int homeworkId;
    private int solutionId;
    private String userFirstName;
    private String userLastName;
    private String userUsername;
    private String text;
    private LocalDateTime date;

}
