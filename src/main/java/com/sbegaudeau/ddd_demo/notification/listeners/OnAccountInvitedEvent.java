package com.sbegaudeau.ddd_demo.notification.listeners;

import com.sbegaudeau.ddd_demo.account.IAccountRepository;
import com.sbegaudeau.ddd_demo.events.AccountInvitedEvent;
import com.sbegaudeau.ddd_demo.notification.NotificationCreationService;
import com.sbegaudeau.ddd_demo.organization.IOrganizationRepository;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class OnAccountInvitedEvent {

    private final IAccountRepository accountRepository;

    private final IOrganizationRepository organizationRepository;

    private final NotificationCreationService notificationCreationService;

    public OnAccountInvitedEvent(IAccountRepository accountRepository, IOrganizationRepository organizationRepository, NotificationCreationService notificationCreationService) {
        this.accountRepository = accountRepository;
        this.organizationRepository = organizationRepository;
        this.notificationCreationService = notificationCreationService;
    }

    @Transactional
    @TransactionalEventListener
    public void onAccountInvited(AccountInvitedEvent event) {
        var account = this.accountRepository.findById(event.account().getId()).orElseThrow(() -> new NoSuchElementException("Account not found"));
        var organization = this.organizationRepository.findById(event.organization().getId()).orElseThrow(() -> new NoSuchElementException("Organization not found"));
        organization.getMemberships().stream()
                .filter(membership -> membership.role().equals("admin"))
                .forEach(membership -> notificationCreationService.createNotification(membership.accountId(), "The account " + account.getUsername() + " has been added to the organization"));
    }
}
