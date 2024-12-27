package com.OJT_Mini.Project.OJT_Mini.Project.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import lombok.Data;


@Data

@Document(collection ="EnrollStatus")
public class Enrollment {
	
	
	  @Id
	  @JsonProperty("_id")  
	  private int id;
	  
	  
	  @JsonProperty("studentId")  
	  private int studentId;
	  
	  @JsonProperty("courseId")  
	  private int courseId;
	  
	  @JsonProperty("enrollDate")  
	  private LocalDate enrollDate;
	  
	  @JsonProperty("grade")  
	  private String grade;
	  
	  @JsonProperty("status")  
	  private String status;
	  
	  
	  
}
