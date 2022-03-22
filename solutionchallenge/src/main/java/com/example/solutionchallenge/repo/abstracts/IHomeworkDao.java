package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Homework;
import com.example.solutionchallenge.entities.concretes.Student;
import com.example.solutionchallenge.entities.dtos.SolutionDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHomeworkDao extends JpaRepository<Homework, Integer> {

    Homework findById(int homeworkId);

    List<Homework> getAllByStudents_Username(String username);

    @Query("select new com.example.solutionchallenge.entities.dtos.SolutionDetailDto(s.id,s.description,std.name, std.lastName,s.date,h.id) from Solution s inner join std inner join s.homework h where h.id=homeworkId")
    SolutionDetailDto getSolutionDetailDto(int homeworkId);
}
