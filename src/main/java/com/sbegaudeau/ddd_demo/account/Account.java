package com.sbegaudeau.ddd_demo.account;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("account")
public class Account {
    @Id
    private UUID id;

    private String username;

    private String email;

    private boolean notificationByEmail;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setNotificationByEmail(boolean notificationByEmail) {
        this.notificationByEmail = notificationByEmail;
    }

    public boolean isNotificationByEmail() {
        return notificationByEmail;
    }
}
