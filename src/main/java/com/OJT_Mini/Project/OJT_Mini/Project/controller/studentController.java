package com.OJT_Mini.Project.OJT_Mini.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OJT_Mini.Project.OJT_Mini.Project.dto.StudentLogin;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Student;
import com.OJT_Mini.Project.OJT_Mini.Project.repository.StudentRepository;
import com.OJT_Mini.Project.OJT_Mini.Project.service.Studentservice;


@RestController
@RequestMapping("api/student")
@CrossOrigin

public class studentController {
	
	@Autowired 
	Studentservice studentservice;
	
	@PostMapping("/addStudent")
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		try {
			String msg=  studentservice.addStudents(student);
			return ResponseEntity.status(HttpStatus.CREATED).body(msg);
		}
		catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
	}
	
	@PostMapping("/studentlogin")
	public String studentLogin(@RequestBody StudentLogin studentLogin ) {
		return studentservice.studentLogin(studentLogin);
		
	}
	
	@PostMapping("/updateStudent")
	public String updateStudent(@RequestBody Student student) {
		return studentservice.updateStudent(student);
	}
	
	@GetMapping("/getAlldetails")
	public List<Student> getAllDetails(){
		return studentservice.getAlldetails();
	}
	
	@GetMapping("/validateToken")
	public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token){
		return studentservice.validateToken(token);
	}
	
	@PostMapping("/studentlogout")
	public ResponseEntity<?>  studentlogout(@RequestHeader("Authorization") String token){
		 return studentservice.studentlogout(token);
		
	}
	

	
	
	}
	

	
	
	
	


