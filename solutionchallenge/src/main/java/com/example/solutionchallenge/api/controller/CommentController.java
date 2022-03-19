package com.example.solutionchallenge.api.controller;

import com.example.solutionchallenge.business.abstracts.ICommentService;
import com.example.solutionchallenge.core.api.ControllerBase;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.entities.concretes.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/comments")
public class CommentController extends ControllerBase<Comment, ICommentService> {

    ICommentService iCommentService;

    public CommentController(ICommentService iCommentService){
        super(iCommentService);
        this.iCommentService = iCommentService;
    }

//     @PostMapping("/addcommenttohomework")
//    ResponseEntity<?> addCommentToHomework(@RequestParam int commentId, int homeworkId){
//        var result = iCommentService.addCommentToHomework(commentId,homeworkId);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new ErrorResult(result.getMessage()),HttpStatus.BAD_REQUEST);
//     }

    @PostMapping("/addcommenttosolution")
    ResponseEntity<?> addCommentToSolution(@RequestParam int commentId, int solutionId){
        var result = iCommentService.addCommentToSolution(commentId,solutionId);
        if (result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addcommenttouser")
    ResponseEntity<?> addCommentToUser(@RequestParam int commentId, int userId){
        var result = iCommentService.addCommentToUser(commentId, userId);
        if (result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);

    }
}
