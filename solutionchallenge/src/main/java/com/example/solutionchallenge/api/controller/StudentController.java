package com.example.solutionchallenge.api.controller;


import com.example.solutionchallenge.business.abstracts.IStudentService;
import com.example.solutionchallenge.core.api.ControllerBase;
import com.example.solutionchallenge.entities.concretes.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
public class StudentController extends ControllerBase<Student, IStudentService> {

    IStudentService iStudentService;

    public StudentController(IStudentService iStudentService){
        super(iStudentService);
        this.iStudentService = iStudentService;
    }
}
