package com.sbegaudeau.ddd_demo.organization;

import org.springframework.data.relational.core.mapping.Table;

@Table("webhook")
public record Webhook(String url) {
}
