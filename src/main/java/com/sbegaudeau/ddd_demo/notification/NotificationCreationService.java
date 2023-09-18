package com.sbegaudeau.ddd_demo.notification;

import java.util.UUID;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationCreationService {

    private final INotificationRepository notificationRepository;

    public NotificationCreationService(INotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void createNotification(UUID accountId, String body) {
        var notification = Notification.newNotification()
                .account(AggregateReference.to(accountId))
                .body(body)
                .build();
        this.notificationRepository.save(notification);
    }
}
