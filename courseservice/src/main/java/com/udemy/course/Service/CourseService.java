package com.udemy.course.Service;

import com.udemy.course.DTO.CourseRequest;
import com.udemy.course.DTO.CourseResponse;
import com.udemy.course.Model.Course;
import com.udemy.course.Model.CourseStatus;
import com.udemy.course.Repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserValidationService userValidationService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public CourseService(CourseRepository courseRepository, UserValidationService userValidationService, RabbitTemplate rabbitTemplate) {
        this.courseRepository = courseRepository;
        this.userValidationService = userValidationService;
        this.rabbitTemplate = rabbitTemplate;
    }


    public CourseResponse getCourseById(String courseId) {
        return courseRepository.findById(courseId).map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Course doesn't exist"));
    }

    public CourseResponse createCourse(String userId,CourseRequest request){

        boolean isValidUser = userValidationService.validateUser(userId);

        if (!isValidUser){
            throw new RuntimeException("Invalid userId");
        }

        Course course = new Course();
        course.setPrice(request.getPrice());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setUserId(userId);

        Course savedCourse = courseRepository.save(course);

        // Publish to RabbitMQ for notification processing

        try {
            rabbitTemplate.convertAndSend(exchange,routingKey,savedCourse);
        }catch(Exception e) {
            log.error("Failed to publish activity to rabbitMQ: ",e);
        }

        return mapToResponse(savedCourse);
    }

    private CourseResponse mapToResponse(Course course){
        CourseResponse courseResponse = new CourseResponse();

        courseResponse.setId(course.getId());
        courseResponse.setCreatedAt(course.getCreatedAt());
        courseResponse.setUpdatedAt(course.getUpdatedAt());
        courseResponse.setTitle(course.getTitle());
        courseResponse.setPrice(course.getPrice());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setStatus(course.getStatus());
        courseResponse.setUserId(course.getUserId());

        return courseResponse;
    }

    public List<CourseResponse> getUserCourses(String userId) {
        List<Course> courses = courseRepository.findAllByUserId(userId);

        return courses.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    public CourseResponse activateCourse(String courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course doesn't exist"));

        if (course.getStatus() == CourseStatus.UNDER_REVIEW ) {
            course.setStatus(CourseStatus.ACTIVE);
        } else if(course.getStatus() == CourseStatus.ACTIVE) {
            throw new RuntimeException("Course is already active");
        } else {
            throw new RuntimeException("The course is banned");
        }



        return mapToResponse(course);
    }
}
