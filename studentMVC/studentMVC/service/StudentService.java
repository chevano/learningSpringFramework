package com.chevano.studentMVC.service;

import java.util.List;

import com.chevano.studentMVC.entity.Student;

public interface StudentService {

	public List<Student> getStudents(int sortedField);

	public void saveStudent(Student student);

	public Student getStudent(int studentId);

	public void deleteStudent(int studentId);
	
	public List<Student> searchStudents(String searchQuery);
}
