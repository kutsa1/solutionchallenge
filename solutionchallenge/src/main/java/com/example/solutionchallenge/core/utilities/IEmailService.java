package com.example.solutionchallenge.core.utilities;

import javax.mail.MessagingException;

public interface IEmailService {
    void sendEmail(String toEmail, String subject, String body) throws MessagingException;
}
