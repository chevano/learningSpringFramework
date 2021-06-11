package com.chevano.studentMVC.dao;

import java.util.List;

import com.chevano.studentMVC.entity.Student;

public interface StudentDAO {
	
	public List<Student> searchStudents(String searchQuery);

	public List<Student> getStudents(int sortField);

	public void saveStudent(Student student);

	public Student getStudent(int studentId);

	public void deleteStudent(int studentId);
	
}
