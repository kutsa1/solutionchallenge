package com.example.solutionchallenge.api.controller;

import com.example.solutionchallenge.business.abstracts.IHomeworkService;
import com.example.solutionchallenge.core.api.ControllerBase;
import com.example.solutionchallenge.core.utilities.results.DataResult;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.entities.concretes.Homework;
import com.example.solutionchallenge.entities.concretes.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/homeworks")
@RestController
public class HomeworkController extends ControllerBase<Homework, IHomeworkService> {

    private final IHomeworkService iHomeworkService;

    public HomeworkController(IHomeworkService iHomeworkService) {
        super(iHomeworkService);
        this.iHomeworkService = iHomeworkService;
    }

    @PostMapping("/addhomeworktouser")
    ResponseEntity<?> addHomeworkToUser(@RequestParam int homeworkId, @RequestParam int userId) {
        var result = iHomeworkService.addHomeworkToUser(homeworkId, userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getallbystudents")
    DataResult<List<Homework>> getAllByStudents(@RequestParam String username) {
        return iHomeworkService.getAllByStudents(username);
    }
}
