package com.forum.microservice.notification.controller;

import com.forum.microservice.notification.entity.NotificationEntity;
import com.forum.microservice.notification.exceptions.NotificationNotFoundException;
import com.forum.microservice.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public List<NotificationEntity> findAll() {
        return notificationService.findAll();
    }

    @GetMapping("/notifications/{notificationId}")
    public NotificationEntity getNotification(@PathVariable int notificiationId) {
        NotificationEntity notification = notificationService.findById(notificiationId);

        if (notification == null) {
            throw new NotificationNotFoundException("Notification id not found: " + notificiationId);
        }

        return notification;
    }
    @GetMapping("/notifications-for-user/{userId}")
    public List<NotificationEntity> getNorificationsForUser(@PathVariable int userId) {

        return notificationService.getNotificationsForUser(userId);
    }
    @PostMapping("/notifications")
    public NotificationEntity addNotification(@RequestBody NotificationEntity notification) {
        notification.setId(0);
        return notificationService.save(notification);
    }
    @PutMapping("/notifications")
    public NotificationEntity updateNotification(@RequestBody NotificationEntity user) {
        return notificationService.save(user);
    }

    @DeleteMapping("/notifications/{notificationId}")
    public void deleteNotification(@PathVariable int notificiationId) {
        NotificationEntity tempCom = notificationService.findById(notificiationId);

        if (tempCom == null) {
            throw new NotificationNotFoundException("Notification id not found: " + notificiationId);
        }

        notificationService.deleteById(notificiationId);
    }
}
