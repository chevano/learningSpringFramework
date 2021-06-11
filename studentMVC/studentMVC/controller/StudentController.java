package com.chevano.studentMVC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chevano.studentMVC.entity.Student;
import com.chevano.studentMVC.service.StudentService;
import com.chevano.studentMVC.util.SortUtils;

@Controller
@RequestMapping("/student")
public class StudentController {

	// need to inject our student service
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/list")
	public String listStudents(Model theModel, @RequestParam(required=false) String sortBy) {
		
		// get students from the service
		List<Student> students = null;
		
		// check for sorted field
		if (sortBy != null) {
			int sortField = Integer.parseInt(sortBy);
			students = studentService.getStudents(sortField);			
		}
		else {
			// no sort field provided then default to sorting by major
			students = studentService.getStudents(SortUtils.MAJOR);
		}
		
		// add the students to the model
		theModel.addAttribute("students", students);
		
		return "list-students";
	}
	
	@GetMapping("/save")
	public String save(Model theModel) {
		
		// create model attribute to bind form data
		Student student = new Student();
		
		theModel.addAttribute("student", student);
		
		return "student-form";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student) {
		
		// save the student using our service
		studentService.saveStudent(student);	
		
		return "redirect:/student/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("studentId") int studentId,
									Model theModel) {
		
		// get the student from our service
		Student student = studentService.getStudent(studentId);	
		
		// set student as a model attribute to pre-populate the form
		theModel.addAttribute("student", student);
		
		// send over to our form		
		return "student-form";
	}
	
	@GetMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int studentId) {
		
		// delete a student
		studentService.deleteStudent(studentId);
		
		return "redirect:/student/list";
	}
	
	@GetMapping("/search")
	public String searchStudents(@RequestParam("searchQuery") String searchQuery,
									Model theModel) {

		// search students from the service
		List<Student> students = studentService.searchStudents(searchQuery);
				
		// add the students to the model
		theModel.addAttribute("students", students);

		return "list-students";		
	}

}










