package com.chevano.studentMVC.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.chevano.studentMVC.entity.Student;
import com.chevano.studentMVC.util.SortUtils;

@Repository
public class StudentDAOImpl implements StudentDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Student> getStudents(int sortField) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// determine sort field
		String fieldName = null;
		
		switch (sortField) {
			case SortUtils.FIRST_NAME: 
				fieldName = "firstName";
				break;
			case SortUtils.LAST_NAME:
				fieldName = "lastName";
				break;
			case SortUtils.EMAIL:
				fieldName = "email";
				break;
			case SortUtils.MAJOR:
				fieldName = "major";
				
			default:
				// if nothing matches the default to sort by major
				fieldName = "major";
		}
		
		// HQL query to get all the student info from the database
		// sorted by the field name specify
		String queryString = "from Student order by " + fieldName;
		Query<Student> theQuery = 
				currentSession.createQuery(queryString, Student.class);
		
		// execute query and get result list
		List<Student> students = theQuery.getResultList();
				
		// return the results		
		return students;
	}

	@Override
	public void saveStudent(Student student) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the student
		currentSession.saveOrUpdate(student);
		
	}

	@Override
	public Student getStudent(int studentId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Student student = currentSession.get(Student.class, studentId);
		
		return student;
	}

	@Override
	public void deleteStudent(int studentId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Remove selected student
		Query query = 
				currentSession.createQuery("delete from Student where id=:studentId");
		query.setParameter("studentId", studentId);
		
		query.executeUpdate();		
	}
	
	@Override
	public List<Student> searchStudents(String searchQuery) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = null;
		
		//
		// only search by name if theSearchName is not empty
		//
		if (searchQuery != null && searchQuery.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			query = currentSession.createQuery("from Student where lower(firstName) like :theName or lower(lastName) like :theName", Student.class);
			query.setParameter("theName", "%" + searchQuery.toLowerCase() + "%");

		}
		else {
			// empty searches result into displaying all the student in the db
			query = currentSession.createQuery("from Student", Student.class);			
		}
		
		// execute query and get result list
		List<Student> students = query.getResultList();
				
		// return the results		
		return students;
		
	}

}











