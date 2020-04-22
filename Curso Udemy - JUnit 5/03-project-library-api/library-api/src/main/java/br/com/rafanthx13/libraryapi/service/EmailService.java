package br.com.rafanthx13.libraryapi.service;

import java.util.List;

public interface EmailService {

    void sendMails(String message, List<String> mailsList);
    
}