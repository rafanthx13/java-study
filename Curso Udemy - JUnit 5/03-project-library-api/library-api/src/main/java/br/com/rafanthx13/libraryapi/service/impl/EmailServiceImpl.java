package br.com.rafanthx13.libraryapi.service.impl;

import br.com.rafanthx13.libraryapi.service.EmailService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Vai injetar javaMailSender
public class EmailServiceImpl implements EmailService {

    @Value("${application.mail.default-remetent}")
    private String remetent;

    // Inje
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMails(String message, List<String> mailsList) {

        String[] mails = mailsList.toArray(new String[mailsList.size()]);
        // Construindo mensagem de email. Setamos cada parte
        SimpleMailMessage mailMessage = new SimpleMailMessage(); 
        mailMessage.setFrom(remetent); // Deve-se mudar esse remetente no app.proprieties para algo mais real
        mailMessage.setSubject("Livro com empréstimo atrasado");
        mailMessage.setText(message);
        mailMessage.setTo(mails); // Paara quem vamos enviar: deve ser um array de String e nâo lista

        javaMailSender.send(mailMessage);
    }
}