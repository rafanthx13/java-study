package br.com.rafanthx13.libraryapi.service;

import br.com.rafanthx13.libraryapi.data.entity.Loan;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Esse service realmente será a implementação
@RequiredArgsConstructor
public class ScheduleService {

    private static final String CRON_LATE_LOANS = "0 0 0 1/1 * ?"; // Todo dia: http://www.cronmaker.com/;

    @Value("${application.mail.lateloans.message}") // Ingetar a traves de application.propieties
    private String message; // Mensagem a ser enviada

    private final LoanService loanService;
    private final EmailService emailService;

    @Scheduled(cron = CRON_LATE_LOANS)
    public void sendMailToLateLoans(){
        List<Loan> allLateLoans = loanService.getAllLateLoans(); // Obter todos os emprestimos atrasados
        // Mapeio essa lsita de Loan e vou converter numa lsita de emails
        List<String> mailsList = allLateLoans.stream()
                .map(loan -> loan.getCustomerEmail())
                .collect(Collectors.toList());
        // Uso o Service para enviar o email
        emailService.sendMails(message, mailsList);

    }
}