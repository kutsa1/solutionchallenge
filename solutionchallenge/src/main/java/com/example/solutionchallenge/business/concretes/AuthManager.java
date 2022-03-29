package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.IAuthService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.core.utilities.results.SuccesDataResult;
import com.example.solutionchallenge.core.utilities.results.SuccessResult;
import com.example.solutionchallenge.repo.abstracts.IUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthManager implements IAuthService {


    private final IUserDao iUserDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public IResult passwordReset(String username, String password, String newPassword) {
        var result = BusinessRule.run(isExistByUsernameAndPassword(username, password));
        if (result != null)
            return result;

        var user = iUserDao.findByUsername(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        iUserDao.save(user);
        return new SuccesDataResult<>(Messages.passwordChangedSuccessfully);
    }


    private IResult isExistByUsernameAndPassword(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        var authInfo = authenticationManager.authenticate(authenticationToken);
        if (authInfo.isAuthenticated())
            return new SuccessResult();
        return new ErrorResult(Messages.userNotFound);
    }
}
