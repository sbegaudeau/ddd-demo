package com.sbegaudeau.ddd_demo.notification;

import com.sbegaudeau.ddd_demo.account.IAccountRepository;
import com.sbegaudeau.ddd_demo.email.EmailService;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class NotificationCreationService {

    private final IAccountRepository accountRepository;

    private final INotificationRepository notificationRepository;

    private final TransactionTemplate transactionTemplate;

    private final EmailService emailService;

    public NotificationCreationService(IAccountRepository accountRepository, INotificationRepository notificationRepository, TransactionTemplate transactionTemplate, EmailService emailService) {
        this.accountRepository = accountRepository;
        this.notificationRepository = notificationRepository;
        this.transactionTemplate = transactionTemplate;
        this.emailService = emailService;
    }

    public void createNotification(UUID accountId, String body) {
        this.transactionTemplate.executeWithoutResult(status -> {
            var notification = new Notification();
            notification.setAccountId(accountId);
            notification.setBody(body);
            this.notificationRepository.save(notification);
        });

        var account = this.accountRepository.findById(accountId).orElseThrow(() -> new NoSuchElementException("Account not found"));
        this.emailService.sendEmail(account.getEmail(), body);
    }
}
