package com.OJT_Mini.Project.OJT_Mini.Project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OJT_Mini.Project.OJT_Mini.Project.model.Course;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Enrollment;
import com.OJT_Mini.Project.OJT_Mini.Project.model.Student;
import com.OJT_Mini.Project.OJT_Mini.Project.repository.CourseRepository;
import com.OJT_Mini.Project.OJT_Mini.Project.repository.EnrollmentRespository;
import com.OJT_Mini.Project.OJT_Mini.Project.repository.StudentRepository;



@Service
public class EnrollImpl implements EnrollService {
	
	@Autowired
	private EnrollmentRespository enrollmentRespository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private MailService mailService;

	@Override
    public String enrollNewId(int courseId,String token) {
        
	Optional<Course> existingCourse=courseRepository.findById(courseId);
	Optional<Student> existingStudent=studentRepository.findByToken(token);
	Optional<Student> findingAdminDetails=studentRepository.findByEmail("admin@gmail.com");
	
	Student studname=existingStudent.get();
	Course course= existingCourse.get();
	
	String text = String.format(
		    "Dear %s,\r\n\r\n"
		    + "Congratulations! You have been successfully enrolled in the course: %s.\r\n\r\n"
		    + "We are excited to have you join us and wish you the best of luck in your learning journey. If you have any questions, feel free to reach out to us.\r\n\r\n"
		    + "Warm regards,\r\n"
		    + "%s\r\n",
		    studname.getFirstName()+" "+studname.getLastName(), course.getCourseName(),    findingAdminDetails.get().getFirstName()+" "+findingAdminDetails.get().getLastName()
		);
	if (existingCourse.isEmpty()) {
		return "Invalid Course Id";
	}
	
	if (existingStudent.isEmpty()) {
		return "Invalid Student Id";
	}
	
	
	
		 List<Enrollment> existingEnrollments = enrollmentRespository.findByStudentId(studname.getId());


		    boolean isAlreadyEnrolled = existingEnrollments.stream()
		        .anyMatch(enroll -> enroll.getCourseId()==courseId);

		    if (isAlreadyEnrolled) {
		        return "Student is already enrolled in this course.";
		    }

		  
		    Enrollment enrollment = new Enrollment();
		    enrollment.setStudentId(studname.getId());
		    enrollment.setCourseId(courseId);
		    enrollment.setEnrollDate(LocalDate.now());
		    enrollment.setGrade("A");
		    enrollment.setStatus("Enrolled");

		    // Save the new enrollment
		    enrollmentRespository.save(enrollment);
		    mailService.sendEmail(studname.getEmail(), "MS Enrollment Confirmation", text);

		    return "Student successfully enrolled in the course.";
    }

	@Override
	public List<Enrollment> getAllEnrollCourses() {
		return enrollmentRespository.findAll();
		
	}

	@Override
	public List<Course> getUserCourse(String token) {
		Optional<Student> existingStudent= studentRepository.findByToken(token);
		
		if(existingStudent.isEmpty()) {
			 return Collections.emptyList();
		}
		

	    List<Enrollment> enrollments = enrollmentRespository.findByStudentId(existingStudent.get().getId());

	    List<Course> courses = enrollments.stream()
	            .map(enrollment -> {
	                // Fetch the course for the enrollment
	                Course course = courseRepository.findById(enrollment.getCourseId()).orElse(null);

	                if (enrollment.getStatus().equalsIgnoreCase("Completed")) {
	                  
	                    course.setCourseCredits(1010);
	                }

	                return course;
	            })
	            .filter(course -> course != null) // Filter out null courses
	            .collect(Collectors.toList());

	    return courses;
	}


	@Override
	public String statuschange(int courseId, String token) {
		Optional<Student> existingStudent=studentRepository.findByToken(token);
		Optional<Enrollment> findingCouseandStudent= enrollmentRespository.findByStudentIdAndCourseId(existingStudent.get().getId(), courseId);
		Optional<Course> existingCourse=courseRepository.findById(courseId);
		Optional<Student> findingAdminDetails=studentRepository.findByEmail("admin@gmail.com");
		
		
		Student studname=existingStudent.get();
		Course course= existingCourse.get();
		
		
		
		if (!findingCouseandStudent.isPresent()) {
			return "Can't find the studentId";
		}
		Enrollment enrollDetails= findingCouseandStudent.get();
		
		String text = String.format(
			    "Dear %s,\r\n\r\n"
			    + "Congratulations! You have been successfully completed  the course: %s.\r\n\r\n"
			    + "Your current grade in the course is: %s.\r\n\r\n"
			    + "This course was worth %d credits.\r\n\r\n"
			    + "We are excited to have you join us and wish you the best of luck in your learning journey. If you have any questions, feel free to reach out to us.\r\n\r\n"
			    + "Warm regards,\r\n"
			    + "%s\r\n",
			    studname.getFirstName() + " " + studname.getLastName(), 
			    course.getCourseName(), 
			    enrollDetails.getGrade(), 
			    course.getCourseCredits(),
			    findingAdminDetails.get().getFirstName()+" "+findingAdminDetails.get().getLastName()
			);
		
		 mailService.sendEmail(studname.getEmail(), "MS Completed Confirmation", text);
		enrollDetails.setStatus("Completed");
		enrollmentRespository.save(enrollDetails);
		return "Status change successfully";
	}


	


}
