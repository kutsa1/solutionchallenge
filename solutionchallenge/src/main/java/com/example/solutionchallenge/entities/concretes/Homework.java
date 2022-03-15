package com.example.solutionchallenge.entities.concretes;

import com.example.solutionchallenge.entities.IEntity;
import com.example.solutionchallenge.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "homeworks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Homework implements IEntity {

    @OneToMany(mappedBy = "homework")
    @JsonIgnore
    private List<Solution> solutions = new ArrayList<>();

    @OneToMany(mappedBy = "homework")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "homeworks")
    @JsonIgnore
    private List<Student> students = new ArrayList<>();




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    private String title;

    @NotBlank
    @NotBlank
    private String description;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;
}
