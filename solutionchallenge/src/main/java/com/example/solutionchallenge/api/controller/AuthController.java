package com.example.solutionchallenge.api.controller;

import com.example.solutionchallenge.business.abstracts.IStudentService;
import com.example.solutionchallenge.business.abstracts.IUserService;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.entities.concretes.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthController {
    private final IStudentService iStudentService;
    private final IUserService iUserService;

    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody Student student) {
        var result = iStudentService.add(student);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/passwordreset")
    ResponseEntity<?> passwordReset(@RequestParam String password, @RequestParam String username) {
        var result = iUserService.passwordReset(password, username);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
