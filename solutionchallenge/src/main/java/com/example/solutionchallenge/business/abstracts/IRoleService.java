package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.entities.Role;
import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.DataResult;
import com.example.solutionchallenge.core.utilities.results.IResult;

public interface IRoleService extends IServiceBase<Role> {
    DataResult<Role> findByName(String roleName);

    IResult existByRoleId(int id);
}
