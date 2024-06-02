package org.example.msnotifications.dao.repository;


import org.example.msnotifications.dao.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {

}
