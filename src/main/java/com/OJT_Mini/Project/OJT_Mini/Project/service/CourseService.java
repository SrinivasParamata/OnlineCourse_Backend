package com.OJT_Mini.Project.OJT_Mini.Project.service;

import java.util.List;

import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;

public interface CourseService {
	
	String addcourse(Course course);

	List<Course> getallCourses();

	String delcourse(int courseId);

	String editcourse(Course course);


}
