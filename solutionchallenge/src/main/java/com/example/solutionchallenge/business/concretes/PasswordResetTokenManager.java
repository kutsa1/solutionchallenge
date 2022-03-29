package com.example.solutionchallenge.business.concretes;

import com.example.solutionchallenge.business.abstracts.IResetTokenService;
import com.example.solutionchallenge.business.abstracts.IUserService;
import com.example.solutionchallenge.business.tools.Messages;
import com.example.solutionchallenge.core.entities.PasswordResetToken;
import com.example.solutionchallenge.core.entities.User;
import com.example.solutionchallenge.core.utilities.business.BusinessRule;
import com.example.solutionchallenge.core.utilities.results.ErrorResult;
import com.example.solutionchallenge.core.utilities.results.IResult;
import com.example.solutionchallenge.core.utilities.results.SuccessResult;
import com.example.solutionchallenge.repo.abstracts.IResetTokenDao;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenManager implements IResetTokenService {
    private final IUserService iUserService;
    private final IResetTokenDao iResetTokenDao;
    private final JavaMailSender javaMailSender;



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
    public IResult sendPasswordResetLink(String email) {

        var result = BusinessRule.run(isExistUserByEmail(email)
        );
        if (result != null)
            return result;
        String token;
        while (true) {
            token = UUID.randomUUID().toString();
            if (!isExistToken(token).isSuccess())
                break;

        }
        User user = iUserService.getUserByEmail(email).getData();
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setToken(token);
        myToken.setExpiryDate(new Date(System.currentTimeMillis()+3600000));
        myToken.setStatus(true);
        myToken.setUser(user);
        iResetTokenDao.save(myToken);
        String subject = "Şifre Değişikliliği Hakkında";
        String body = "https://localhost:4200/reset-password/" + token;

        sendEmail(email,subject,body);
        return new SuccessResult(myToken.getToken());
    }
    private void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kutsalgurlek99@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);
    }


}
