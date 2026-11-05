package com.notification.notificationservice.Controller;

import com.notification.notificationservice.DTO.NotificationRequest;
import com.notification.notificationservice.DTO.NotificationResponse;
import com.notification.notificationservice.Model.Notification;
import com.notification.notificationservice.Service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationRequest request){
        return ResponseEntity.ok(notificationService.createNotification(request));
    }
}
