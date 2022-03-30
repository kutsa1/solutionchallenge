package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.utilities.results.IResult;

public interface IAuthService {
    IResult passwordReset(String username, String password, String newPassword);

    IResult passwordResetWithToken(String token, String password);

    IResult isTokenExpired(String token);

}
