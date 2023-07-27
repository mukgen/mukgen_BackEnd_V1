package com.example.mukgen.infra.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@RequiredArgsConstructor
@Transactional
@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    private final CodeRepository codeRepository;

    private final ValidMailRepository validMailRepository;

    Random random = new Random();

    public void sendMail(
            SendMailRequest request
    ) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String code = makeCode();

        if (codeRepository.existsById(request.getMail()))
            codeRepository.deleteById(request.getMail());

        codeRepository.save(new Code(request.getMail(), code));

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
        int randomValue;

        do {

            for (int i = 0; i < 6; i++)
                code += (int) (random.nextDouble() * 10);
        } while (codeRepository.existsByCode(code));

        return code;
    }

    public void validMail(
            ValidMailRequest request
    ) {

        Code code = codeRepository.findById(request.getMail())
                .orElseThrow();

        if (!code.getCode().equals(request.getCode()))
            throw new RuntimeException();

        validMailRepository.save(request.getMail());
    }
}