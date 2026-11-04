package com.udemy.course.Controller;

import com.udemy.course.DTO.CourseRequest;
import com.udemy.course.DTO.CourseResponse;
import com.udemy.course.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable String courseId){
        System.out.println(courseId);
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest request){
        return ResponseEntity.ok(courseService.createCourse(request));
    }
}
