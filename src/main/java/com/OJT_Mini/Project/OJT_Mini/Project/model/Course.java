package com.OJT_Mini.Project.OJT_Mini.Project.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.OJT_Mini.Project.OJT_Mini.Project.dto.StudentLogin;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;



@Data

@Document(collection ="courses")
public class Course {
	
	@Id
	@JsonProperty("_id")  
	private int id;
	
	@JsonProperty("courseName")
	private String courseName;
	
	@JsonProperty("courseCode")
	private String courseCode;
	
	@JsonProperty("courseDescrp")
	private String courseDescrp;
	
	@JsonProperty("courseCredits")
	private int courseCredits;
	


}
