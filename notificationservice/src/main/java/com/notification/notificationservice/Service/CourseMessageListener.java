package com.notification.notificationservice.Service;

import com.notification.notificationservice.Model.Course;
import com.notification.notificationservice.Model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseMessageListener {

    private final NotificationService notificationService;

    public CourseMessageListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "course.queue")
    public void processCourse(Course course){
        Notification notification = notificationService.createNotification(course);

        log.info("A notification has been created with the ID of: {}",notification.getId());
    }
}
