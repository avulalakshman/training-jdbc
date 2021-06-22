package com.heraizen.hibernatecore;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.heraizen.hibernatecore.domain.Department;
import com.heraizen.hibernatecore.domain.Student;

public class Manager {

	public static void main(String[] args) {

//		Student student = new Student();
//		student.setName("Krish");
//		student.setEmail("krish@gmail.com");
//		student.setMobile("9876543210");
//
//		student = addStudent(student);
//		System.out.println(student);
		System.out.println(getStudent(1L));
	}

	
	private static Student getStudent(Long id) {
		Student student=null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			student=session.find(Student.class, id);
			session.close();
		} catch (Exception e) {
			System.out.println("While getting student :" + e);
		}
		return student;

	}

	private static Student addStudent(Student student) {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			Long id = (Long) session.save(student);
			student.setId(id);
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println("While adding student :" + e);
		}
		return student;

	}

	private static List<Department> getDeptDetails() {
		List<Department> list = new ArrayList<>();
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			list = session.createQuery("from Department", Department.class).list(); // JPQL
			session.close();
		} catch (HibernateException e) {
			System.out.println("While getting dept details:" + e);
		}
		return list;
	}
}
