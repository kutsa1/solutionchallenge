package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Student;
import com.example.solutionchallenge.entities.dtos.StudentDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStudentDao extends JpaRepository<Student, Integer> {
    List<Student> findByName(String studentName);

    Student findById(int studentId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("select new com.example.solutionchallenge.entities.dtos.StudentDetailDto(s.id,s.name,s.lastName) from Student s where s.username=:username")
    StudentDetailDto getStudentDetailDto(String username);
}
