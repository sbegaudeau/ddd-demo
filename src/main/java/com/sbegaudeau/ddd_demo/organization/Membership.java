package com.sbegaudeau.ddd_demo.organization;

import java.util.UUID;

import org.springframework.data.relational.core.mapping.Table;

@Table("membership")
public record Membership(UUID accountId, String role) {
}
