package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.core.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResetTokenDao extends JpaRepository<PasswordResetToken, Integer> {
    boolean existsByToken(String token);

    PasswordResetToken getByToken(String token);
}
