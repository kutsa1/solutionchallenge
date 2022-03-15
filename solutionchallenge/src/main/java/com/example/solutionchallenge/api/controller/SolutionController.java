package com.example.solutionchallenge.api.controller;

import com.example.solutionchallenge.business.abstracts.ISolutionService;
import com.example.solutionchallenge.core.api.ControllerBase;
import com.example.solutionchallenge.entities.concretes.Solution;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/solutions")
public class SolutionController extends ControllerBase<Solution, ISolutionService> {

    ISolutionService iSolutionService;

    public SolutionController(ISolutionService iSolutionService) {
        super(iSolutionService);
        this.iSolutionService = iSolutionService;
    }
}
