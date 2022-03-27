package com.example.solutionchallenge.api.controller;


import com.example.solutionchallenge.business.abstracts.IStudentService;
import com.example.solutionchallenge.core.api.ControllerBase;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.entities.concretes.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students")
public class StudentController extends ControllerBase<Student, IStudentService> {

    IStudentService iStudentService;

    public StudentController(IStudentService iStudentService) {
        super(iStudentService);
        this.iStudentService = iStudentService;
    }

    @GetMapping("/getstudentdetail")
    ResponseEntity<?> getStudentDetail(@RequestParam String username) {
        var result = iStudentService.getStudentDetailDto(username);
        if (result.isSuccess())
            return new ResponseEntity(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getstudenteditdto")
    ResponseEntity<?> getStudentEditDto(@RequestParam String username) {
        var result = iStudentService.getStudentDtoByUsername(username);
        if (result.isSuccess())
            return new ResponseEntity(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
