package com.student.student.service;

import java.util.List;

import com.student.student.entity.Student;
import com.student.student.exception.IdNotFoundException;

public interface StudentService {
	
	public Student addStudent(Student student);
	public Student getStudentById(int id) throws IdNotFoundException;
	public Student updateStudent(Student student);
	public Student deleteStudent(int id);
	public List<Student> getAllStudents();
	

}
