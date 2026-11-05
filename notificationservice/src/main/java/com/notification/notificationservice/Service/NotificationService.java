package com.notification.notificationservice.Service;

import com.notification.notificationservice.DTO.NotificationResponse;
import com.notification.notificationservice.Model.Course;
import com.notification.notificationservice.Model.Notification;
import com.notification.notificationservice.Model.NotificationType;
import com.notification.notificationservice.Repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Course course) {
        Notification notification = new Notification();
        notification.setMessage("The course of " + course.getTitle() + "has been created ,and is under review.");
        notification.setType(NotificationType.SYSTEM);
        notification.setUserId(course.getUserId());

        Notification savedNotification = notificationRepository.save(notification);

        return savedNotification;
    }

    public List<NotificationResponse> getUserNotifications(String userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);

        return notifications.stream()
                .map(this::mapToNotificationResponse)
                .collect(Collectors.toList());
    }

    private NotificationResponse mapToNotificationResponse(Notification notification) {
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setId(notification.getId());
        notificationResponse.setUserId(notification.getUserId());
        notificationResponse.setCreatedAt(notification.getCreatedAt());
        notificationResponse.setMessage(notification.getMessage());
        notificationResponse.setType(notification.getType());
        notificationResponse.setNotifierId(notification.getNotifierId());

        return notificationResponse;
    }
}
