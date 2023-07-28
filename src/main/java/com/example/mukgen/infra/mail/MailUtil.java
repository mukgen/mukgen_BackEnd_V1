package com.example.mukgen.infra.mail;

import com.example.mukgen.domain.mail.controller.dto.request.SendMailRequest;
import com.example.mukgen.domain.mail.entity.Code;
import com.example.mukgen.domain.mail.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class MailUtil {

    private final JavaMailSender javaMailSender;

    private final CodeRepository codeRepository;

    Random random = new Random();

    public void sendMail(SendMailRequest request) {

        String code = makeCode();

        if (codeRepository.existsById(request.getMail())) {
            codeRepository.deleteById(request.getMail());
        }

        codeRepository.save(new Code(request.getMail(), code));

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String mailSubject = "[ 먹젠 ] 이메일 인증 코드입니다.";

        String mailText = "이메일 인증 코드입니다 : " + code +
                "\n해당 코드는 5분간 유효합니다.";

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            mimeMessageHelper.setTo(request.getMail());
            mimeMessageHelper.setSubject(mailSubject);
            mimeMessageHelper.setText(mailText, false);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String makeCode() {

        String code = "";

        do {
            for (int i = 0; i < 6; i++)
                code += (int) (random.nextDouble() * 10);
        } while (codeRepository.existsByCode(code));

        return code;
    }
}
