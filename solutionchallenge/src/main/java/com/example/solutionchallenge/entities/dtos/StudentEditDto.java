package com.example.solutionchallenge.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentEditDto {
    private int id;
    private String name;
    private String lastName;
    private String username;
    private String email;
}
