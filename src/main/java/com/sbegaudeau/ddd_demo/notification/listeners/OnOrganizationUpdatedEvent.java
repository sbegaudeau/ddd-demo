package com.sbegaudeau.ddd_demo.notification.listeners;

import com.sbegaudeau.ddd_demo.events.OrganizationUpdatedEvent;
import com.sbegaudeau.ddd_demo.notification.NotificationCreationService;
import com.sbegaudeau.ddd_demo.organization.IOrganizationRepository;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class OnOrganizationUpdatedEvent {

    private final IOrganizationRepository organizationRepository;

    private final NotificationCreationService notificationCreationService;

    public OnOrganizationUpdatedEvent(IOrganizationRepository organizationRepository, NotificationCreationService notificationCreationService) {
        this.organizationRepository = organizationRepository;
        this.notificationCreationService = notificationCreationService;
    }

    @Transactional
    @TransactionalEventListener
    public void onOrganizationUpdated(OrganizationUpdatedEvent event) {
        var organization = this.organizationRepository.findById(event.organization().getId()).orElseThrow(() -> new NoSuchElementException("Organization not found"));
        organization.getMemberships()
                .forEach(membership -> notificationCreationService.createNotification(membership.accountId(), "The organization has been updated"));
    }
}
