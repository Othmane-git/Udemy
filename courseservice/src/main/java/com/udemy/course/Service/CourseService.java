package com.udemy.course.Service;

import com.udemy.course.DTO.CourseRequest;
import com.udemy.course.DTO.CourseResponse;
import com.udemy.course.Model.Course;
import com.udemy.course.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public CourseResponse getCourse(String courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course doesn't exist"));

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

    public CourseResponse createCourse(String userId,CourseRequest request){
        Course course = new Course();
        course.setPrice(request.getPrice());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setUserId(userId);

        Course savedCourse = courseRepository.save(course);

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(savedCourse.getId());
        courseResponse.setCreatedAt(savedCourse.getCreatedAt());
        courseResponse.setUpdatedAt(savedCourse.getUpdatedAt());
        courseResponse.setTitle(savedCourse.getTitle());
        courseResponse.setPrice(savedCourse.getPrice());
        courseResponse.setDescription(savedCourse.getDescription());
        courseResponse.setStatus(savedCourse.getStatus());
        courseResponse.setUserId(userId);

        return courseResponse;
    }

    public List<CourseResponse> getUserCourses(String userId) {
        List<Course> courses = courseRepository.findAllByUserId(userId);
        List<CourseResponse> courseResponses = new java.util.ArrayList<>(List.of());

        for (Course course : courses){
            CourseResponse courseResponse = new CourseResponse();

            courseResponse.setId(course.getId());
            courseResponse.setCreatedAt(course.getCreatedAt());
            courseResponse.setUpdatedAt(course.getUpdatedAt());
            courseResponse.setTitle(course.getTitle());
            courseResponse.setPrice(course.getPrice());
            courseResponse.setDescription(course.getDescription());
            courseResponse.setStatus(course.getStatus());
            courseResponse.setUserId(userId);

            courseResponses.add(courseResponse);
        }

        return courseResponses;
    }
}
