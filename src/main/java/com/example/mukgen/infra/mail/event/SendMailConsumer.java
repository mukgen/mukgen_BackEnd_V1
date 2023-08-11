package com.example.mukgen.infra.mail.event;

import com.example.mukgen.domain.mail.controller.dto.request.SendMailRequest;
import com.example.mukgen.infra.mail.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendMailConsumer {

    private final MailUtil mailUtil;

    @KafkaListener(topics = "SEND_MAIL", groupId = "mukgen", containerFactory = "sendMailListener")
    public void sendMail(SendMailRequest request){
        mailUtil.sendMail(request);
    }

}
