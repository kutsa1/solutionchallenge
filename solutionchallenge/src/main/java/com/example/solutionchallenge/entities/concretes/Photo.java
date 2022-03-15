package com.example.solutionchallenge.entities.concretes;

import com.example.solutionchallenge.entities.IEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "photos")
@NoArgsConstructor
@AllArgsConstructor
public class Photo  implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @NotNull
    private String imgUrl;

    @ManyToOne
    @JsonIgnore
    private Solution solution;


}
