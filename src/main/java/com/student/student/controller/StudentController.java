package com.student.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.student.entity.Student;
import com.student.student.exception.IdNotFoundException;
import com.student.student.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping("/student")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		
		ResponseEntity<Student> response=new ResponseEntity<Student>(service.addStudent(student), HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/student")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student){
		try {
			service.getStudentById(student.getStudentId());
		ResponseEntity<Student> response=new ResponseEntity<Student>(service.addStudent(student), HttpStatus.OK);
		return response;
		} catch(IdNotFoundException e) {
			System.out.println("error");
		}
		return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/student")
	public ResponseEntity<Student> deleteStudent(@PathVariable int studentId){
		try {
			service.getStudentById(studentId);
			service.deleteStudent(studentId);
			ResponseEntity<Student> response=new ResponseEntity<Student>(HttpStatus.OK);
			
		return response;
		} catch(IdNotFoundException e) {
			System.out.println("error");
		}
		return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
	}
	
	

}
