package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.DataResult;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.entities.concretes.Homework;
import com.example.solutionchallenge.entities.concretes.Student;

import java.util.List;

public interface IHomeworkService extends IServiceBase<Homework> {
    IResult addHomeworkToUser(int homeworkId, int studentId);

    DataResult<List<Homework>> getAllByStudents(String  username);


}
