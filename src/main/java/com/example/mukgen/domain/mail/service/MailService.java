package com.example.mukgen.domain.mail.service;

import com.example.mukgen.domain.auth.service.exception.CodeMismatchException;
import com.example.mukgen.domain.mail.controller.dto.request.SendMailRequest;
import com.example.mukgen.domain.mail.controller.dto.request.AuthenticateMailRequest;
import com.example.mukgen.domain.mail.entity.Code;
import com.example.mukgen.domain.mail.entity.AuthenticatedMail;
import com.example.mukgen.domain.mail.repository.CodeRepository;
import com.example.mukgen.domain.mail.repository.AuthenticatedMailRepository;
import com.example.mukgen.domain.mail.service.exception.UnAuthenticatedMailException;
import com.example.mukgen.infra.mail.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MailService {

    private final MailUtil mailUtil;

    private final KafkaTemplate<String, SendMailRequest> sendMailRequestKafkaTemplate;

    private final CodeRepository codeRepository;

    private final AuthenticatedMailRepository authenticatedMailRepository;

    public void sendMail(SendMailRequest request) {
        mailUtil.sendMail(request);
    }

    public void authenticateMail(
            AuthenticateMailRequest request
    ) {

        Code code = codeRepository.findById(request.getMail())
                .orElseThrow(() -> UnAuthenticatedMailException.EXCEPTION);

        if (!code.getCode().equals(request.getCode())) {
            throw CodeMismatchException.EXCEPTION;
        }

        authenticatedMailRepository.save(new AuthenticatedMail(request.getMail()));
    }
}