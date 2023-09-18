package com.sbegaudeau.ddd_demo.email.listeners;

import com.sbegaudeau.ddd_demo.account.IAccountRepository;
import com.sbegaudeau.ddd_demo.email.EmailService;
import com.sbegaudeau.ddd_demo.events.NotificationSentEvent;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class OnNotificationSentEvent {

    private final IAccountRepository accountRepository;

    private final EmailService emailService;

    public OnNotificationSentEvent(IAccountRepository accountRepository, EmailService emailService) {
        this.accountRepository = accountRepository;
        this.emailService = emailService;
    }

    @Transactional
    @TransactionalEventListener
    public void onNotificationSent(NotificationSentEvent event) {
        var account = this.accountRepository.findById(event.account().getId()).orElseThrow(() -> new NoSuchElementException("Account not found"));
        this.emailService.sendEmail(account.getEmail(), event.body());
    }
}
