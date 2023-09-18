package com.sbegaudeau.ddd_demo.email;

import java.time.Instant;

import org.springframework.transaction.annotation.Transactional;

public class EmailService {

    private final IEmailEntryRepository emailEntryRepository;

    public EmailService(IEmailEntryRepository emailEntryRepository) {
        this.emailEntryRepository = emailEntryRepository;
    }

    @Transactional
    public void sendEmail(String email, String body) {
        // Sends email using a remote service

        var emailEntry = new EmailEntry();
        emailEntry.setEmail(email);
        emailEntry.setWhen(Instant.now());
        this.emailEntryRepository.save(emailEntry);
    }
}
