package com.sbegaudeau.ddd_demo.organization;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationUpdateService {
    private final IOrganizationRepository organizationRepository;

    public OrganizationUpdateService(IOrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public void renameOrganization(UUID organizationId, String newName) {
        var organization = this.organizationRepository.findById(organizationId).orElseThrow(() -> new NoSuchElementException("Organization not found"));
        organization.setName(newName);
        this.organizationRepository.save(organization);

        // Track changes and notify all members...
    }
}
