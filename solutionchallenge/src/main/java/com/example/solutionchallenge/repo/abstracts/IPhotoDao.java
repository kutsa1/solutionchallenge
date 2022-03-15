package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhotoDao extends JpaRepository<Photo,Integer> {
}
