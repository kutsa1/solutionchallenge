package com.example.solutionchallenge.api.controller;

import com.example.solutionchallenge.business.abstracts.IUserService;
import com.example.solutionchallenge.core.api.ControllerBase;
import com.example.solutionchallenge.core.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class
UserController extends ControllerBase<User,IUserService> {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        super(iUserService);
        this.iUserService = iUserService;
    }


    @PostMapping("/addroletouser")
    public ResponseEntity<?> addRoleToUser(@RequestParam String username, @RequestParam String roleName) {
        var result = iUserService.addRoleToUser(username, roleName);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(result.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
