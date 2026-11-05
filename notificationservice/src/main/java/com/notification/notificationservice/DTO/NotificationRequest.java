package com.notification.notificationservice.DTO;

import jakarta.validation.constraints.NotBlank;

public class NotificationRequest {
    @NotBlank
    private String userId;
    private String notifierId;
    @NotBlank
    private String message;
}
