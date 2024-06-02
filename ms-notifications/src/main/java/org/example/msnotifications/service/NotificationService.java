package org.example.msnotifications.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.msnotifications.dao.entity.NotificationEntity;
import org.example.msnotifications.dao.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotificationService {

    NotificationRepository notificationRepository;

    public void sendNotification(NotificationEntity notifications){
        notificationRepository.save(notifications);
        log.info("ActionLog.sendNotification.successful: {}",notifications);
    }

}
