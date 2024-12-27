package com.OJT_Mini.Project.OJT_Mini.Project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Enrollment;


public interface EnrollmentRespository extends MongoRepository<Enrollment, Integer> {
	

	Optional<Enrollment>  findByStudentIdAndCourseId(int studentId,int courseId);
	List<Enrollment> findByStudentId(int studentId);
	List<Enrollment> findByCourseId(int courseId);
	void deleteByCourseId(int courseId);
	

	
}
