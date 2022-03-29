package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.entities.User;
import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.DataResult;
import com.example.solutionchallenge.core.utilities.results.IResult;

public interface IUserService extends IServiceBase<User> {
    IResult addRoleToUser(String username, String roleName);

    DataResult<User> getByUsername(String username);

    DataResult<User> getById(Integer id);

    IResult existsById(int userId);

}
