package com.example.solutionchallenge.entities.concretes;



import com.example.solutionchallenge.core.entities.IEntity;
import com.example.solutionchallenge.core.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment implements IEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @NotBlank
    @NotNull
    private Student student;

    private boolean status = true;
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @NotBlank
    @NotNull
    private Solution solution;

    @ManyToOne
    @NotBlank
    @NotNull
    private Homework homework;

    @NotNull
    @NotBlank
    private String text;


}
