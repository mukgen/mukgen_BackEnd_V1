package com.example.mukgen.infra.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/mail")
@RestController
public class MailController {

    private final MailService mailService;

    @GetMapping
    public void mailSend(
            @RequestBody SendMailRequest request
    ) {
        mailService.sendMail(request);
    }

    @PostMapping
    public void mailValid(
            @RequestBody ValidMailRequest request
    ) {
        mailService.validMail(request);
    }
}
