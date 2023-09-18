package com.sbegaudeau.ddd_demo.email;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("email_entry")
public class EmailEntry {
    @Id
    private UUID id;

    private String email;

    private Instant when;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setWhen(Instant when) {
        this.when = when;
    }

    public Instant getWhen() {
        return when;
    }
}
