package com.example.solutionchallenge.api.controller;

import com.example.solutionchallenge.business.abstracts.ISolutionService;
import com.example.solutionchallenge.core.api.ControllerBase;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.entities.concretes.Solution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/getallsolutiondetail")
    ResponseEntity<?> getAllSolutionDetail() {
        var result = iSolutionService.getAllSolutionDetail();
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
