package com.forum.microservice.notification.repository;

import com.forum.microservice.notification.entity.NotificationEntity;
import com.forum.microservice.notification.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
    List<NotificationEntity> findByReceiver(UserEntity receiver);
}
