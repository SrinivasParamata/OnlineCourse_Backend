package com.OJT_Mini.Project.OJT_Mini.Project.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.OJT_Mini.Project.OJT_Mini.Project.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {
	
	Optional<Student> findByEmail(String mail);
	Optional<Student> findByEmailAndPassword(String email , String password);
	Optional<Student> findById(int Id);
	Optional<Student> findByToken(String token);


}
