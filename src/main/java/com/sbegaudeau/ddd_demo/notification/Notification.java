package com.sbegaudeau.ddd_demo.notification;

import com.sbegaudeau.ddd_demo.account.Account;
import com.sbegaudeau.ddd_demo.events.NotificationSentEvent;

import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Table("notification")
public class Notification extends AbstractAggregateRoot<Notification> {

    @Id
    private UUID id;

    private UUID accountId;

    private String body;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getBody() {
        return body;
    }

    public static NotificationBuilder newNotification() {
        return new NotificationBuilder();
    }

    public static class NotificationBuilder {
        private AggregateReference<Account, UUID> account;

        private String body;

        public NotificationBuilder account(AggregateReference<Account, UUID> account) {
            this.account = Objects.requireNonNull(account);
            return this;
        }

        public NotificationBuilder body(String body) {
            this.body = Objects.requireNonNull(body);
            if (body.isBlank()) {
               throw new IllegalArgumentException("The body cannot be blank");
            }
            return this;
        }

        public Notification build() {
            var notification = new Notification();
            notification.accountId = this.account.getId();
            notification.body = Objects.requireNonNull(this.body);

            notification.registerEvent(new NotificationSentEvent(this.account, this.body));

            return notification;
        }
    }
}
