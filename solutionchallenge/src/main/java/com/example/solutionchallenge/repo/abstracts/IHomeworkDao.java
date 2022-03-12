package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHomeworkDao extends JpaRepository<Homework, Integer> {

    List<Homework> findById(int homeworkId);
}
