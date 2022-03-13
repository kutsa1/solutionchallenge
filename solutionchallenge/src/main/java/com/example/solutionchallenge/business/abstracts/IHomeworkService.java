package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.entities.concretes.Homework;

public interface IHomeworkService extends IServiceBase<Homework> {
    IResult addHomeworkToUser(int reportId, int userId);
    IResult existsById(int homeworkId);
}
