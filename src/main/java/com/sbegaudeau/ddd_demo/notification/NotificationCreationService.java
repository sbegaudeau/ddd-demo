package com.sbegaudeau.ddd_demo.notification;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class NotificationCreationService {

    private final INotificationRepository notificationRepository;

    public NotificationCreationService(INotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(UUID accountId, String body) {

    }
}
