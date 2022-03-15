package com.example.solutionchallenge.api.controller;

import com.example.solutionchallenge.business.abstracts.IHomeworkService;
import com.example.solutionchallenge.core.api.ControllerBase;
import com.example.solutionchallenge.entities.concretes.Homework;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/homeworks")
@RestController
public class HomeworkController extends ControllerBase<Homework, IHomeworkService> {

    IHomeworkService iHomeworkService;

    public HomeworkController(IHomeworkService iHomeworkService){
        super(iHomeworkService);
        this.iHomeworkService=iHomeworkService;
    }
}
