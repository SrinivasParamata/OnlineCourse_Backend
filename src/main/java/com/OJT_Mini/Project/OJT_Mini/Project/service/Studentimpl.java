package com.OJT_Mini.Project.OJT_Mini.Project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.OJT_Mini.Project.OJT_Mini.Project.Util.JwtUtil;
import com.OJT_Mini.Project.OJT_Mini.Project.dto.StudentLogin;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Student;
import com.OJT_Mini.Project.OJT_Mini.Project.repository.CourseRepository;
import com.OJT_Mini.Project.OJT_Mini.Project.repository.StudentRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Studentimpl implements Studentservice  {
	
	@Autowired
	StudentRepository studentrepository;
	
	@Autowired
	PasswordService passwordService;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	JwtUtil jwtUtil;
	
	
	
	

	
	public String addStudents(Student student) {
		
		
        String email = student.getEmail();
        if (studentrepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email ID is already registered.");
        }

        validateStudent(student);
        

        studentrepository.save(student);
        return "Student registered successfully.";
    }

    private void validateStudent(Student student) {
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        String email = student.getEmail();
        String phone = student.getPhone();
        String address = student.getAddress();
        String password = student.getPassword();

        if (firstName == null || firstName.length() <= 3 || !firstName.matches("^[A-Za-z]+$")) {
            throw new RuntimeException("First name must be more than 3 letters and contain only alphabets.");
        }

        if (lastName == null || lastName.length() <= 3 || !lastName.matches("^[A-Za-z]+$")) {
            throw new RuntimeException("Last name must be more than 3 letters and contain only alphabets.");
        }


        if (email == null || (!email.endsWith("@gmail.com") && !email.endsWith("@email.com"))) {
            throw new RuntimeException("Email must end with @gmail.com or @email.com.");
        }

        if (phone == null || phone.length() != 10 || !phone.matches("\\d{10}")) {
            throw new RuntimeException("Phone number must contain exactly 10 digits.");
        }

        if (address == null || address.trim().isEmpty()) {
            throw new RuntimeException("Address cannot be empty.");
        }

    }


	@Override
	public String studentLogin(StudentLogin studentLogin) {
		
		Optional<Student> existingUser=studentrepository.findByEmailAndPassword(studentLogin.getEmail(),studentLogin.getPassword());
		

		if (existingUser.isPresent()) {
			Student stud = existingUser.get();
			stud.setToken(jwtUtil.generateToken(stud.getFirstName()+stud.getLastName()));	
			studentrepository.save(stud);
			try {
				String studDetails = new ObjectMapper().writeValueAsString(stud);
				  return "{\"message\":\"Old User\", \"user\": " + studDetails + ", \"token\": \"" + jwtUtil.generateToken(stud.getFirstName()+stud.getLastName()) + "\"}";
			}
			catch(Exception e) {
				return "{\"message\":\"Error processing user data\"}";
			}
		}
		return  "{\"message\":\"Invalid credentials\"}";
		
		
		
	}

	@Override
	public String updateStudent(Student student) {
		
		
	     Student existingUser = studentrepository.findById(student.getId())
	                .orElseThrow(() -> new RuntimeException("Student ID is invalid."));
	     if (!existingUser.getEmail().equals(student.getEmail()) && studentrepository.findByEmail(student.getEmail()).isPresent()) {
	    	    throw new RuntimeException("Email ID is already registered.");
	    	}


	            validateStudent(student);

	            existingUser.setFirstName(student.getFirstName());
	            existingUser.setLastName(student.getLastName());
	            existingUser.setEmail(student.getEmail());
	            existingUser.setPhone(student.getPhone());
	            existingUser.setAddress(student.getAddress());

	            studentrepository.save(existingUser);

	            return "Student updated successfully.";
	        
		
	}

	@Override
	public List<Student> getAlldetails() {
		return studentrepository.findAll();
	}
	
	
	@Override
	public ResponseEntity<?> validateToken(String token) {
	    Optional<Student> existingStudent = studentrepository.findByToken(token);
	    System.out.println("Token received: " + token);
	    
	    // Check if the student exists for the given token
	    if (existingStudent.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token not found");
	    }

	    try {
	        
	        boolean isValid = jwtUtil.validateToken(token, existingStudent.get().getFirstName() + existingStudent.get().getLastName());

	        if (!isValid) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid or expired");
	        }

	        return ResponseEntity.ok("Access granted");

	    } catch (Exception e) {
	        // Handle unexpected errors
	        System.err.println("Error processing the token: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the token");
	    }
	}

	@Override
	public ResponseEntity<?> studentlogout(String token) {
	    Optional<Student> existingStudent = studentrepository.findByToken(token);

	    // Check if the student exists
	    if (existingStudent.isPresent()) {
	        existingStudent.get().setToken(null);
	        studentrepository.save(existingStudent.get());  // Save the student with the null token


	        return ResponseEntity.ok("Logout successful");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid or expired");
	    }
	}

		
		
	}
