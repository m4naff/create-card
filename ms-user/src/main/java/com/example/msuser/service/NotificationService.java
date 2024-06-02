package com.example.msuser.service;

import com.example.msuser.client.NotificationClient;
import com.example.msuser.dto.client.request.NotificationsRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NotificationService {

    NotificationClient notificationClient;

        public void sendNotification(NotificationsRequest notifications) {
        notificationClient.sendNotification(notifications);
    }

}
