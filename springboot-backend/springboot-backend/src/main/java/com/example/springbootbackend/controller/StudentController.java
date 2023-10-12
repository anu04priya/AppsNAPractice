package com.example.springbootbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootbackend.Repository.StudentRepository;
import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Student;
//import com.matrimony.Entity.Customer;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@GetMapping("/students/{rollNo}")
	public ResponseEntity<Student>getStudentById(@PathVariable Long rollNo) {
		Student student = studentRepository.findById(rollNo).orElseThrow(() -> new ResourceNotFoundException("Student not exist with rollNo: "+rollNo));
		return ResponseEntity.ok(student);
	}
	
	@PutMapping("/students/{rollNo}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long rollNo,@RequestBody Student studentDetails){
		Student student = studentRepository.findById(rollNo)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with rollNo: " + rollNo));
		
		student.setFirstName(studentDetails.getFirstName());
	    student.setLastName(studentDetails.getLastName());
	    student.setDepartment(studentDetails.getDepartment());
	    
	    Student updatedStudent = studentRepository.save(student);
	    return ResponseEntity.ok(updatedStudent);
		
	}
	@DeleteMapping("/students/{rollNo}")
	public ResponseEntity <Map<String, Boolean>> deleteStudent(@PathVariable Long rollNo){
		Student student =  studentRepository.findById(rollNo)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with rollNo: "+rollNo));
		
		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
		
	}
}


