package com.example.solutionchallenge.repo.abstracts;

import com.example.solutionchallenge.entities.concretes.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentDao extends JpaRepository<Comment, Integer> {

    Comment findById(int commentId);
}
