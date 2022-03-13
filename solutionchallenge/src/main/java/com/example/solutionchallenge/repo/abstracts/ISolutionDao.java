package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISolutionDao extends JpaRepository<Solution, Integer> {

    Solution findById(int solutionId);
}
