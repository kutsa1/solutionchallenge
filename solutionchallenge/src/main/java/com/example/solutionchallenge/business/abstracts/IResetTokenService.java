package com.example.solutionchallenge.business.abstracts;

import com.example.solutionchallenge.core.entities.PasswordResetToken;
import com.example.solutionchallenge.core.utilities.business.IServiceBase;
import com.example.solutionchallenge.core.utilities.results.IResult;

import javax.mail.MessagingException;

public interface IResetTokenService {

    IResult sendPasswordResetLink(String email) throws MessagingException;

}
