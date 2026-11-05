package com.notification.notificationservice.DTO;

import com.notification.notificationservice.Model.NotificationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {
    private String id;
    private String userId;
    private NotificationType type;
    private String notifierId;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
