package com.forum.microservice.notification.service;

import com.forum.microservice.notification.entity.NotificationEntity;
import com.forum.microservice.notification.entity.UserEntity;
import com.forum.microservice.notification.exceptions.NotificationNotFoundException;
import com.forum.microservice.notification.exceptions.UserNotFoundException;
import com.forum.microservice.notification.repository.NotificationRepository;
import com.forum.microservice.notification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    private UserRepository userRepository;

    @Autowired
    public NotificationServiceImpl(
            NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<NotificationEntity> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public NotificationEntity findById(int id) {
        Optional<NotificationEntity> foundEntity = notificationRepository.findById(id);
        if (foundEntity.isPresent()) {
            return foundEntity.get();
        } else {
            throw new NotificationNotFoundException("Notification id not found: " + id);
        }
    }

    @Override
    public List<NotificationEntity> getNotificationsForUser(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return notificationRepository.findByReceiver(user.get());
        } else {
            throw new UserNotFoundException("User id not found: " + userId);
        }
    }

    @Override
    public NotificationEntity save(NotificationEntity notificationEntity) {
        return notificationRepository.save(notificationEntity);
    }

    @Override
    public void deleteById(int id) {
        notificationRepository.deleteById(id);
    }
}
