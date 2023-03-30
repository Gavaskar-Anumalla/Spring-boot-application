package com.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Model.Student;
import com.demo.Repository.StudentRepo;

@Service
public class StudentService {

	@Autowired
	StudentRepo repo;

	public Student addStudent(Student student) {
		return repo.save(student);

	}

	public Optional<Student> getStudentById(int id) {

		Optional<Student> student = repo.findById(id);

		return student;
	}

}
