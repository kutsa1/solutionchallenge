package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.DataResult;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.entities.concretes.Comment;
import com.example.solutionchallenge.entities.dtos.CommentDetailDto;

import java.util.List;

public interface ICommentService extends IServiceBase<Comment> {

    IResult addCommentToUser(int commentId, int userId);

    IResult addCommentToSolution(int commentId, int solutionId);

    DataResult<List<CommentDetailDto>> getAllCommentDtoByHomeworkId(int homeworkId);

}
