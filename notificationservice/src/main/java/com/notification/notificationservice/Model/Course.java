package com.notification.notificationservice.Model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Course {
    private String id;
    private String userId;
    private String title;
    private String description;
    private BigDecimal price;
    private CourseStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
