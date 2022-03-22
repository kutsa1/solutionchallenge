package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Solution;
import com.example.solutionchallenge.entities.dtos.SolutionDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISolutionDao extends JpaRepository<Solution, Integer> {

    Solution findById(int solutionId);


    @Query("select new com.example.solutionchallenge.entities.dtos.SolutionDetailDto(s.id,s.description,std.name,std.lastName,s.date,h.id) from Solution s inner join s.student std inner join s.homework h")
    List<SolutionDetailDto> getAllSolutionDetail();
}
