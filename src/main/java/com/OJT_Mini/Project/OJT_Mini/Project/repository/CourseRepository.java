package com.OJT_Mini.Project.OJT_Mini.Project.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;

public interface CourseRepository extends MongoRepository<Course, Integer> {
	
	Optional<Course> findByCourseName(String courseName);
	Optional<Course> findById(int courseId);
//	Optional<Course> findByCourseId(int courseId);
//	
//	




}
