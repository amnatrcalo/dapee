package com.forum.microservice.notification.service;

import com.forum.microservice.notification.entity.NotificationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    List<NotificationEntity> findAll();

    NotificationEntity findById(int id);

    List<NotificationEntity> getNotificationsForUser(int userId);
    NotificationEntity save(NotificationEntity notificationEntity);

    void deleteById(int id);
}
