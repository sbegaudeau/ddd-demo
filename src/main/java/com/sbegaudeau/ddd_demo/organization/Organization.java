package com.sbegaudeau.ddd_demo.organization;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("organization")
public class Organization {

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }
}
