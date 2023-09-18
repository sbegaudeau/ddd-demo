package com.sbegaudeau.ddd_demo.webhook.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbegaudeau.ddd_demo.events.IOrganizationEvent;
import com.sbegaudeau.ddd_demo.organization.IOrganizationRepository;

import java.net.URI;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OnOrganizationEvent {

    private final IOrganizationRepository organizationRepository;

    private final ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(OnOrganizationEvent.class);

    public OnOrganizationEvent(IOrganizationRepository organizationRepository, ObjectMapper objectMapper) {
        this.organizationRepository = organizationRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    @TransactionalEventListener
    public void onOrganization(IOrganizationEvent event) {
        var organization = this.organizationRepository.findById(event.organization().getId()).orElseThrow(() -> new NoSuchElementException("Organization not found"));
        organization.getWebhooks().forEach(webhook -> {
            var url = webhook.url();
            try {
                var body = this.objectMapper.writeValueAsString(event);

                var client = WebClient.builder()
                        .build();

                client.post()
                        .uri(URI.create(url))
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
            } catch (JsonProcessingException exception) {
                logger.warn(exception.getMessage());
            }
        });
    }
}
