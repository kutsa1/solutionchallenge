package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDao extends JpaRepository <Role, Integer> {

    boolean existsByName(String roleName);

    Role findByName(String roleName);
}
