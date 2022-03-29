package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.core.entities.PasswordResetToken;
import com.example.solutionchallenge.core.entities.User;
import com.example.solutionchallenge.core.utilities.results.IResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResetTokenDao extends JpaRepository<PasswordResetToken, Integer> {
    boolean existsByToken(String token);

}
