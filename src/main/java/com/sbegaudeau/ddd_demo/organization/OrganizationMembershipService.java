package com.sbegaudeau.ddd_demo.organization;

import com.sbegaudeau.ddd_demo.account.IAccountRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationMembershipService {

    private final IAccountRepository accountRepository;

    private final IOrganizationRepository organizationRepository;

    public OrganizationMembershipService(IAccountRepository accountRepository, IOrganizationRepository organizationRepository) {
        this.accountRepository = accountRepository;
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public void inviteMember(UUID organizationId, String username, String role) {
        var account = this.accountRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("Account not found"));
        var organization = this.organizationRepository.findById(organizationId).orElseThrow(() -> new NoSuchElementException("Organization not found"));

        var membership = new Membership(account.getId(), role);
        organization.getMemberships().add(membership);
        this.organizationRepository.save(organization);
    }
}
