package org.example.msnotifications.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.msnotifications.dao.entity.NotificationEntity;
import org.example.msnotifications.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("notifications")
public class NotificationController {
    NotificationService notificationService;

    @PostMapping("send")
    public void sendNotification(@RequestBody NotificationEntity notifications){
        notificationService.sendNotification(notifications);
    }


}
