package com.demo.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Exception.RecordNotFoundException;
import com.demo.Model.Student;
import com.demo.Service.StudentService;

@RestController
@RequestMapping("/Student")
public class StudentController {

	@Autowired
	StudentService service;

	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
		Student s = service.addStudent(student);

		return new ResponseEntity<Student>(s, HttpStatus.OK);

	}

	@GetMapping("/findById/{empid}")
	public ResponseEntity<Student> getStudentById(@PathVariable("empid") int id) {
		Optional<Student> student = service.getStudentById(id);

		// Student s=student.get();
		if (student.isPresent()) {
			return new ResponseEntity<Student>(student.get(), HttpStatus.OK);

		} else {
			throw new RecordNotFoundException("Given id is not available...");
		}

	}
}