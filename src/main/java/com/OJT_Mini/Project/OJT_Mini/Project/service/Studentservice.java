package com.OJT_Mini.Project.OJT_Mini.Project.service;



import java.util.List;

import org.springframework.http.ResponseEntity;

import com.OJT_Mini.Project.OJT_Mini.Project.dto.StudentLogin;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Student;


public interface Studentservice {

	String addStudents(Student student);

	String studentLogin(StudentLogin studentLogin);

	String updateStudent(Student student);

	List<Student> getAlldetails();

	ResponseEntity<?> validateToken(String token);

	ResponseEntity<?> studentlogout(String token);

	
	


}
