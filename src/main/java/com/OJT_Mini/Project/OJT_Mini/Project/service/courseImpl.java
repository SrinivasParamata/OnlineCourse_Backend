package com.OJT_Mini.Project.OJT_Mini.Project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Enrollment;
import com.OJT_Mini.Project.OJT_Mini.Project.repository.CourseRepository;
import com.OJT_Mini.Project.OJT_Mini.Project.repository.EnrollmentRespository;

import jakarta.transaction.Transactional;



@Service
public class courseImpl implements CourseService {
	
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EnrollmentRespository enrollmentRespository;
	
	

	@Override
	public String addcourse(Course course) {
		Optional<Course> existingCourseName= courseRepository.findByCourseName(course.getCourseName());
		
		
		if (existingCourseName.isPresent()) {
			 return "Course is already registered";
		}
		 		
		courseRepository.save(course);
		return "Course added sucessfully";
	
	}

	@Override
	public List<Course> getallCourses() {
		return courseRepository.findAll();
	}

	@Override
	public String delcourse(int courseId) {
	    // Check if the course exists
	    Optional<Course> courseOptional = courseRepository.findById(courseId);
	    
	    if (courseOptional.isEmpty()) {
	        return "Course does not exist.";
	    }
	    
	    List<Enrollment> existingCourses= enrollmentRespository.findByCourseId(courseId);
	    if (!existingCourses.isEmpty()) {
	        enrollmentRespository.deleteByCourseId(courseId);
	    }
	    
	    
	    courseRepository.deleteById(courseId);
	    return "Course has successfully deleted.";
	}

	@Override
	public String editcourse(Course course) {
		Optional<Course> existingcourse= courseRepository.findByCourseName(course.getCourseName());
		
		if (!existingcourse.isPresent()) {
			return "Course not found";
		}
		
		Course updatecourse= existingcourse.get();
		
		updatecourse.setCourseDescrp(course.getCourseDescrp());
		updatecourse.setCourseCredits(course.getCourseCredits());
		updatecourse.setCourseCode(course.getCourseCode());
		courseRepository.save(updatecourse);
		return "Course has been updated";
		
	}


}
