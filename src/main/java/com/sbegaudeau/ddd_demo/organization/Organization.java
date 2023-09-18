package com.sbegaudeau.ddd_demo.organization;

import com.sbegaudeau.ddd_demo.account.Account;
import com.sbegaudeau.ddd_demo.events.AccountInvitedEvent;
import com.sbegaudeau.ddd_demo.events.OrganizationUpdatedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table("organization")
public class Organization extends AbstractAggregateRoot<Organization> {

    @Id
    private UUID id;

    private String name;

    private List<Membership> memberships = new ArrayList<>();

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Membership> getMemberships() {
        return Collections.unmodifiableList(memberships);
    }

    public void updateName(String newName) {
        this.name = Objects.requireNonNull(newName);

        this.registerEvent(new OrganizationUpdatedEvent(AggregateReference.to(this.id)));
    }

    public void inviteMember(AggregateReference<Account, UUID> account, String role) {
        Objects.requireNonNull(role);
        var membership = new Membership(account.getId(), role);
        this.memberships.add(membership);

        this.registerEvent(new AccountInvitedEvent(AggregateReference.to(this.id), account));
    }
}
