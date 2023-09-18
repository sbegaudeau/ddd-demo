package com.sbegaudeau.ddd_demo.events;

import com.sbegaudeau.ddd_demo.organization.Organization;

import java.util.UUID;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

public record OrganizationUpdatedEvent(
        AggregateReference<Organization, UUID> organization
) {
}
