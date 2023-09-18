package com.sbegaudeau.ddd_demo.events;

import com.sbegaudeau.ddd_demo.account.Account;

import java.util.UUID;

import org.springframework.data.jdbc.core.mapping.AggregateReference;

public record NotificationSentEvent(
        AggregateReference<Account, UUID> account,
        String body
) {
}
