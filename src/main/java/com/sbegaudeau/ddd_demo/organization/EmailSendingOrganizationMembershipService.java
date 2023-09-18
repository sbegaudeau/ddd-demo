package com.sbegaudeau.ddd_demo.organization;

import com.sbegaudeau.ddd_demo.account.IAccountRepository;
import com.sbegaudeau.ddd_demo.email.EmailService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class EmailSendingOrganizationMembershipService {

    private final IAccountRepository accountRepository;

    private final IOrganizationRepository organizationRepository;

    private final NotifyingOrganizationMembershipService notifyingOrganizationMembershipService;

    private final TransactionTemplate transactionTemplate;

    private final EmailService emailService;

    public EmailSendingOrganizationMembershipService(IAccountRepository accountRepository, IOrganizationRepository organizationRepository, NotifyingOrganizationMembershipService notifyingOrganizationMembershipService, TransactionTemplate transactionTemplate, EmailService emailService) {
        this.accountRepository = accountRepository;
        this.organizationRepository = organizationRepository;
        this.notifyingOrganizationMembershipService = notifyingOrganizationMembershipService;
        this.transactionTemplate = transactionTemplate;
        this.emailService = emailService;
    }
    public void inviteMember(UUID organizationId, String username, String role) {
        this.transactionTemplate.executeWithoutResult(status -> this.notifyingOrganizationMembershipService.inviteMember(organizationId, username, role));

        var organization = this.organizationRepository.findById(organizationId).orElseThrow(() -> new NoSuchElementException("Organization not found"));
        organization.getMemberships().stream()
                .filter(membership -> membership.role().equals("admin"))
                .map(membership -> this.accountRepository.findById(membership.accountId()))
                .flatMap(Optional::stream)
                .forEach(account -> this.emailService.sendEmail(account.getEmail(), "The account " + username + " has been added to the organization"));
    }
}
