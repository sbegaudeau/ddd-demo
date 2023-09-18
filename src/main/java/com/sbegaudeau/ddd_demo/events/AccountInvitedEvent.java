package com.sbegaudeau.ddd_demo.events;

import com.sbegaudeau.ddd_demo.account.Account;
import com.sbegaudeau.ddd_demo.organization.Organization;

import java.util.UUID;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

public record AccountInvitedEvent (
        AggregateReference<Organization, UUID> organization,
        AggregateReference<Account, UUID> account
) implements IDomainEvent {
}
