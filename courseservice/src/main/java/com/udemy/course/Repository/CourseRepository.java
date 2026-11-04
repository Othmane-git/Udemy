package com.udemy.course.Repository;

import com.udemy.course.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

    List<Course> findAllByUserId(String userId);
}
