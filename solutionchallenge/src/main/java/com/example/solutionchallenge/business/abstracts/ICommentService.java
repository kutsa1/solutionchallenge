package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.entities.concretes.Comment;

public interface ICommentService extends IServiceBase<Comment> {
    IResult addCommentToHomework (int commentId, int homeworkId);
}
