package com.example.solutionchallenge.core.utilities.business;

import com.example.solutionchallenge.core.utilities.results.IResult;

public class BusinessRule {
    public static IResult run(IResult... logics) {
        for (var logic : logics) {
            if (!logic.isSuccess())
                return logic;
        }
        return null;
    }
}
