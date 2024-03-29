package com.example.mukgen.domain.mail.controller;

import com.example.mukgen.domain.mail.controller.dto.request.SendMailRequest;
import com.example.mukgen.domain.mail.controller.dto.request.AuthenticateMailRequest;
import com.example.mukgen.domain.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/mukgen/mail")
@RestController
public class MailController {

    private final MailService mailService;

    @PostMapping("/send")
    public void mailSend(
            @RequestBody @Valid SendMailRequest request
    ) {
        mailService.sendMail(request);
    }

    @PostMapping("/authenticate")
    public void mailAuthenticate(
            @RequestBody @Valid AuthenticateMailRequest request
    ) {
        mailService.authenticateMail(request);
    }
}
