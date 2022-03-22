package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Homework;
import com.example.solutionchallenge.entities.concretes.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHomeworkDao extends JpaRepository<Homework, Integer> {

    Homework findById(int homeworkId);

    List<Homework> getAllByStudents_Username(String username);
}
