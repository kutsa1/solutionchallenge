package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.IAuthService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.core.utilities.results.SuccesDataResult;
import com.example.solutionchallenge.core.utilities.results.SuccessResult;
import com.example.solutionchallenge.repo.abstracts.IResetTokenDao;
import com.example.solutionchallenge.repo.abstracts.IUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthManager implements IAuthService {


    private final IUserDao iUserDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IResetTokenDao iResetTokenDao;

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

    @Override
    public IResult passwordResetWithToken(String token, String password) {
        var myToken = iResetTokenDao.getByToken(token);

        var user = myToken.getUser();
        user.setPassword(passwordEncoder.encode(password));
        myToken.setStatus(false);
        iUserDao.save(user);
        iResetTokenDao.save(myToken);
        return new SuccessResult(Messages.passwordChangedSuccessfully);
    }

    @Override
    public IResult isTokenExpired(String token) {
        if (!iResetTokenDao.existsByToken(token))
            return new ErrorResult(Messages.tokenNotValid);

        var myToken = iResetTokenDao.getByToken(token);

        if (new Date().after(myToken.getExpiryDate())|| !myToken.isStatus())
            return new ErrorResult(Messages.tokenNotValid);

        return new SuccessResult();

    }


    private IResult isExistByUsernameAndPassword(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        var authInfo = authenticationManager.authenticate(authenticationToken);
        if (authInfo.isAuthenticated())
            return new SuccessResult();
        return new ErrorResult(Messages.userNotFound);
    }
}
