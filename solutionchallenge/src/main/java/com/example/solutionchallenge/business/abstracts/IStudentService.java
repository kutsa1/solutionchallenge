package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.DataResult;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.entities.concretes.Student;

public interface IStudentService extends IServiceBase<Student> {
    DataResult<Student> getById(Integer id);
    IResult existById(int studentId);
}
