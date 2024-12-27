package com.OJT_Mini.Project.OJT_Mini.Project.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import lombok.Data;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;


@Data

@Document(collection ="students")
public class Student {
	
	@Id
    @JsonProperty("_id")  
    private int id;
	
	@JsonProperty("firstName")
	private String firstName;
	
	
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("DOB")
	private String DOB;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("token")
	private String token;
	

	


	
	
	
	
	
	
	

}
