package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentDao extends JpaRepository<Student, Integer> {
    List<Student> findByName(String studentName);
    Student findById(int studentId);
}
