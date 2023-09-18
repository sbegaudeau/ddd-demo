package com.sbegaudeau.ddd_demo.organization;

import com.sbegaudeau.ddd_demo.notification.NotificationCreationService;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotifyingOrganizationMembershipService {

    private final IOrganizationRepository organizationRepository;

    private final OrganizationMembershipService organizationMembershipService;

    private final NotificationCreationService notificationCreationService;

    public NotifyingOrganizationMembershipService(IOrganizationRepository organizationRepository, OrganizationMembershipService organizationMembershipService, NotificationCreationService notificationCreationService) {
        this.organizationRepository = organizationRepository;
        this.organizationMembershipService = organizationMembershipService;
        this.notificationCreationService = notificationCreationService;
    }

    @Transactional
    public void inviteMember(UUID organizationId, String username, String role) {
        this.organizationMembershipService.inviteMember(organizationId, username, role);

        var organization = this.organizationRepository.findById(organizationId).orElseThrow(() -> new NoSuchElementException("Organization not found"));
        organization.getMemberships().stream()
                .filter(membership -> membership.role().equals("admin"))
                .forEach(membership -> notificationCreationService.createNotification(membership.accountId(), "The account " + username + " has been added to the organization"));
    }
}
