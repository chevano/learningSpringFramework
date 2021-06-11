package com.chevano.studentMVC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chevano.studentMVC.dao.StudentDAO;
import com.chevano.studentMVC.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	// need to inject customer dao
	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	@Transactional
	public List<Student> getStudents(int sortedField) {
		return studentDAO.getStudents(sortedField);
	}

	@Override
	@Transactional
	public void saveStudent(Student student) {

		studentDAO.saveStudent(student);
	}

	@Override
	@Transactional
	public Student getStudent(int studentId) {
		
		return studentDAO.getStudent(studentId);
	}

	@Override
	@Transactional
	public void deleteStudent(int studentId) {
		
		studentDAO.deleteStudent(studentId);
	}
	
	@Override
	@Transactional
	public List<Student> searchStudents(String searchQuery) {

		return studentDAO.searchStudents(searchQuery);
	}

}





