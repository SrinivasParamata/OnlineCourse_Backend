package com.OJT_Mini.Project.OJT_Mini.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OJT_Mini.Project.OJT_Mini.Project.dto.StudentLogin;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;
import com.OJT_Mini.Project.OJT_Mini.Project.repository.CourseRepository;
import com.OJT_Mini.Project.OJT_Mini.Project.service.CourseService;

@RestController
@RequestMapping("api/course")
@CrossOrigin
public class courseController {
	
	@Autowired
	CourseService courseService;
	
	@PostMapping("/addcourse")
	public String addCourse(@RequestBody Course course) {
		return courseService.addcourse(course);
	}
	
	
	@GetMapping("/getCourses")
	public List<Course> getCourses(){
		return courseService.getallCourses();
	}
	
	@PostMapping("/delcourse/{courseId}")
	public String delcourse(@PathVariable("courseId") int courseId) {
		return courseService.delcourse(courseId);
	}
	
	@PostMapping("/editCourse")
	public String editCourse(@RequestBody Course course ) {
		return courseService.editcourse(course);
	}
	
	

}
