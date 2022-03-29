package com.example.solutionchallenge;

import com.example.solutionchallenge.business.abstracts.IRoleService;
import com.example.solutionchallenge.business.abstracts.IUserService;
import com.example.solutionchallenge.core.entities.Role;
import com.example.solutionchallenge.core.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SolutionChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolutionChallengeApplication.class, args);

    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//	    @Bean
//		CommandLineRunner run(IUserService userService, IRoleService roleService) {
//        return args -> {
//
//            userService.add(new User(0, "isoo", "test@test1.com", "12345", true, new ArrayList<>()));
//            userService.add(new User(0, "kutsa1", "gürlek@gürlek.com", "12345", true, new ArrayList<>()));
//
//            roleService.add(new Role(0, "admin"));
//            roleService.add(new Role(0, "user"));
//
//            userService.addRoleToUser("isoo", "admin");
//            userService.addRoleToUser("kutsa1", "admin");
//        };
//    }
    

}
