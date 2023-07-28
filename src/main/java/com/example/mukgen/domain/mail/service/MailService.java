package com.example.mukgen.domain.mail.service;

import com.example.mukgen.domain.auth.service.exception.CodeMismatchException;
import com.example.mukgen.domain.mail.controller.dto.request.SendMailRequest;
import com.example.mukgen.domain.mail.controller.dto.request.ValidMailRequest;
import com.example.mukgen.domain.mail.entity.Code;
import com.example.mukgen.domain.mail.entity.ValidMail;
import com.example.mukgen.domain.mail.repository.CodeRepository;
import com.example.mukgen.domain.mail.repository.ValidMailRepository;
import com.example.mukgen.domain.mail.service.exception.NeverValidMailException;
import com.example.mukgen.infra.mail.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MailService {

    private final CodeRepository codeRepository;

    private final ValidMailRepository validMailRepository;

    private final MailUtil mailUtil;

    public void sendMail(SendMailRequest request) {
        mailUtil.sendMail(request);
    }

    public void validMail(
            ValidMailRequest request
    ) {

        Code code = codeRepository.findById(request.getMail())
                .orElseThrow(() -> NeverValidMailException.EXCEPTION);

        if (!code.getCode().equals(request.getCode())) {
            throw CodeMismatchException.EXCEPTION;
        }

        validMailRepository.save(new ValidMail(request.getMail()));
    }
}