package com.OJT_Mini.Project.OJT_Mini.Project.service;

import java.util.List;

import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Enrollment;

public interface EnrollService {

	String enrollNewId(int courseId, String token);

	List<Enrollment> getAllEnrollCourses();

	List<Course> getUserCourse(String token);

	String statuschange(int courseId, String token);


}
