package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISolutionDao extends JpaRepository<Solution, Integer> {

    List<Solution> findById(int solutionId);
}
