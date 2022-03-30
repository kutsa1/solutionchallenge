package com.example.solutionchallenge.api.controller;


import com.example.solutionchallenge.business.abstracts.IAuthService;
import com.example.solutionchallenge.business.abstracts.IResetTokenService;
import com.example.solutionchallenge.business.abstracts.IStudentService;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.entities.concretes.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthController {
    private final IStudentService iStudentService;
    private final IAuthService iAuthService;
    private final IResetTokenService iResetTokenService;


    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody Student student) {
        var result = iStudentService.add(student);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/passwordreset")
    ResponseEntity<?> passwordReset(@RequestParam String username, @RequestParam String password, @RequestParam String newPassword) {
        var result = iAuthService.passwordReset(username, password, newPassword);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/passwordresetbyemail")
    ResponseEntity<?> passwordResetByEmail(@RequestParam String email) throws MessagingException {
        var result = iResetTokenService.sendPasswordResetLink(email);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/istokenvalid")
    ResponseEntity<?> isTokenValid(@RequestParam String token) {
        var result = iAuthService.isTokenExpired(token);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/passwordresetbytoken")
    ResponseEntity<?> passwordResetByToken(@RequestParam String token, @RequestParam String password) {
        var result = iAuthService.passwordResetWithToken(token, password);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
