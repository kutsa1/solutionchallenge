package com.example.solutionchallenge.core.utilities.business;

import com.example.solutionchallenge.entities.IEntity;
import com.example.solutionchallenge.core.utilities.results.DataResult;
import com.example.solutionchallenge.core.utilities.results.IResult;

import java.util.List;

public interface IServiceBase<T extends IEntity> {
    DataResult<List<T>> getAll();

    IResult add(T t);

    IResult update(T t);

    IResult delete(T t);

    DataResult<T> getById(Integer id);

}
