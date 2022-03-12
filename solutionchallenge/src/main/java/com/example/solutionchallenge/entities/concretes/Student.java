package com.example.solutionchallenge.entities.concretes;

import com.example.solutionchallenge.core.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class Student extends User {

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Homework> homeworks = new ArrayList<>();

    @NotNull
    @NotBlank
    @Size(min = 2)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 2)
    private String lastName;

    @NotNull
    @NotBlank
    private String gender;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthDay;

}
