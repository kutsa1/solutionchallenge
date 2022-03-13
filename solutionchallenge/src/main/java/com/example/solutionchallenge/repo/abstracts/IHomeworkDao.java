package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHomeworkDao extends JpaRepository<Homework, Integer> {

    Homework findById(int homeworkId);
}
