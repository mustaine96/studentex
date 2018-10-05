package com.student.student;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.student.student.controller.StudentController;
import com.student.student.entity.Student;
import com.student.student.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentApplicationTests {

	
	@Mock
	private StudentService service;

	@InjectMocks
	private StudentController controller;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testAddStudent() throws Exception {

		String content = "{\"studentId\": 123,\"studentName\": \"vipul\", \"studentCourse\": \"javacloud\"}";

		Student student=new Student(123,"vipul","javacloud");
		
		when(service.addStudent(Mockito.isA(Student.class))).thenReturn(student);
		
		mockMvc.perform(post("/student").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.studentId").exists()).andExpect(jsonPath("$.studentName").exists());
		
		verify(service).addStudent(Mockito.isA(Student.class));
	}

}
