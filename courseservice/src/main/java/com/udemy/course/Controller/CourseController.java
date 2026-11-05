package com.udemy.course.Controller;

import com.udemy.course.DTO.CourseRequest;
import com.udemy.course.DTO.CourseResponse;
import com.udemy.course.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public ResponseEntity<List<CourseResponse>> getUserCourses(@RequestHeader("X-User-ID") String userId){
        return ResponseEntity.ok(courseService.getUserCourses(userId));
    }


    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable String courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PatchMapping("/{courseId}/activate")
    public ResponseEntity<CourseResponse> activateCourse(@PathVariable String courseId){
        return ResponseEntity.ok(courseService.activateCourse(courseId));
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestHeader("X-User-ID") String userId,@RequestBody CourseRequest request){
        return ResponseEntity.ok(courseService.createCourse(userId,request));
    }
}
