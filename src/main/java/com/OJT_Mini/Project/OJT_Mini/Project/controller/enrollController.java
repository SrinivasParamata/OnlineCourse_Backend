package com.OJT_Mini.Project.OJT_Mini.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Enrollment;
import com.OJT_Mini.Project.OJT_Mini.Project.service.EnrollService;


@RestController
@RequestMapping("api/enroll")
@CrossOrigin

public class enrollController {
	
	@Autowired
	private EnrollService enrollService;
	
	@PostMapping("/enrollNewId/{CourseId}")
	public String enrollNewId(@PathVariable("CourseId") int CourseId, @RequestHeader("Authorization") String token) {
		return  enrollService.enrollNewId(CourseId ,token);
	}
	
	@GetMapping("/getAllEnrollcourses")
	public List<Enrollment> getAllEnrollCourses(){
		return enrollService.getAllEnrollCourses();
		
	}
	
	 @GetMapping("/getUserCourses")
	    public List<Course> getUserCourse(@RequestHeader("Authorization") String token) {
	        return enrollService.getUserCourse(token); // Return courses
	    }
	 
	 @PostMapping("/coursestatus/{courseId}")
	 public String statuschange(@PathVariable("courseId") int courseId, @RequestHeader("Authorization") String token) {
		return  enrollService.statuschange(courseId,token);
	 }
	 

}
