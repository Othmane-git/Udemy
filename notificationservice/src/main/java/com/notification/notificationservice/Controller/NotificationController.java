package com.notification.notificationservice.Controller;

import com.notification.notificationservice.DTO.NotificationResponse;
import com.notification.notificationservice.Service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationResponse>> getUserNotifications(@PathVariable String userId){
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

//    @PostMapping
//    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationRequest request){
//        return ResponseEntity.ok(notificationService.createNotification(request));
//    }
}
