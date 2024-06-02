package com.example.msuser.client;

import com.example.msuser.dto.client.request.NotificationsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-notifications",url = "${spring.url.ms-notifications}")
public interface NotificationClient {
    @PostMapping("send")
    void sendNotification(@RequestBody NotificationsRequest notifications);

}
