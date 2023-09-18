package com.sbegaudeau.ddd_demo.events;

import com.sbegaudeau.ddd_demo.organization.Organization;

import java.util.UUID;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

public interface IOrganizationEvent extends IDomainEvent {
    AggregateReference<Organization, UUID> organization();
}
