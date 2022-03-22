package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.DataResult;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.entities.concretes.Solution;
import com.example.solutionchallenge.entities.dtos.SolutionDetailDto;

import java.util.List;

public interface ISolutionService extends IServiceBase<Solution> {
    DataResult<Solution> getById(Integer id);
    IResult existBySolutionId(int solutionId);

    DataResult<List<SolutionDetailDto>> getAllSolutionDetail();
}
