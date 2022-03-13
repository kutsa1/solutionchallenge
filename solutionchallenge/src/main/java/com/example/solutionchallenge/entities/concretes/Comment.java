package com.example.solutionchallenge.entities.concretes;



import com.example.solutionchallenge.entities.IEntity;
import com.example.solutionchallenge.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @JsonIgnore
    private User user;

    private boolean status = true;
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JsonIgnore
    private Solution solution;

    @ManyToOne
    @JsonIgnore
    private Homework homework;


}
