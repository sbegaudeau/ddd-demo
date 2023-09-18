package com.sbegaudeau.ddd_demo.notification;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends CrudRepository<Notification, UUID> {
}
