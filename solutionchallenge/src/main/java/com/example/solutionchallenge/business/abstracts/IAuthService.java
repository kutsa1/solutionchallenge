package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.utilities.results.IResult;

public interface IAuthService {
    IResult passwordReset(String username, String password, String newPassword);

}
