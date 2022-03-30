package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.IResetTokenService;
import com.example.solutionchallenge.business.abstracts.IUserService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.entities.PasswordResetToken;
import com.example.solutionchallenge.core.entities.User;
import com.example.solutionchallenge.core.utilities.IEmailService;
import com.example.solutionchallenge.core.utilities.Mail;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.core.utilities.results.SuccessResult;
import com.example.solutionchallenge.repo.abstracts.IResetTokenDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenManager implements IResetTokenService {
    private final IUserService iUserService;
    private final IResetTokenDao iResetTokenDao;
    private final IEmailService iEmailService;


    @Value("${reset.token.expire}")
    private String resetPasswordTokenExpireDate;

    @Value("${reset.password.link}")
    private String resetPasswordLink;


    private IResult isExistUserByEmail(String userEmail) {
        var result = iUserService.existsByEmail(userEmail);
        if (!result.isSuccess())
            return new ErrorResult(Messages.userNotFoundByEmail);
        return new SuccessResult();
    }

    private IResult isExistToken(String token) {
        var result = iResetTokenDao.existsByToken(token);
        if (result)
            return new SuccessResult();
        return new ErrorResult(Messages.tokenNotFound);
    }

    @Override
    public IResult sendPasswordResetLink(String email) throws MessagingException {

        var result = BusinessRule.run(isExistUserByEmail(email));
        if (result != null)
            return result;
        String token;
        do {
            token = UUID.randomUUID().toString();

        } while (isExistToken(token).isSuccess());


        User user = iUserService.getUserByEmail(email).getData();
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setToken(token);
        myToken.setExpiryDate(new Date(System.currentTimeMillis() + Long.parseLong(resetPasswordTokenExpireDate)));
        myToken.setStatus(true);
        myToken.setUser(user);
        iResetTokenDao.save(myToken);
        String subject = "Şifre Değişikliği Hakkında";
        String body = Mail.mail(user.getUsername(), resetPasswordLink + token);

        iEmailService.sendEmail(email, subject, body);
        return new SuccessResult(myToken.getToken());
    }


}
