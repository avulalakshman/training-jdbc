package com.heraizen.hibernatecore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.heraizen.hibernatecore.domain.Batch;
import com.heraizen.hibernatecore.domain.Certification;
import com.heraizen.hibernatecore.domain.Department;
import com.heraizen.hibernatecore.domain.Gender;
import com.heraizen.hibernatecore.domain.Student;
import com.heraizen.hibernatecore.domain.Trainer;

public class Manager {

	public static void main(String[] args) {

//		Student student = new Student();
//		student.setName("Krish");
//		student.setEmail("krish@gmail.com");
//		student.setMobile("9876543210");
//
//		student = addStudent(student);
//		System.out.println(student);
		// System.out.println(getStudent(1L));
//		Trainer t1 = new Trainer();
//		t1.setGender(Gender.MALE);
//		t1.setName("Lakshman");
//		t1.getHobbies().addAll(Arrays.asList("Reading","Paying TT"));
//		
//		Certification c1 = new Certification();
//		c1.setName("Sun Certification");
//		c1.setYear(2008);
//		t1.getCertifications().addAll(Arrays.asList(c1));
//		Long id = addTrainer(t1);
//		System.out.println("Trainer is added with id:" + id);
//
//		Trainer t2 = new Trainer();
//		t2.setGender(Gender.FEMALE);
//		t2.setName("Tanvi");
//		t2.getHobbies().addAll(Arrays.asList("Cooking","Paying Badminton"));
//		id = addTrainer(t2);
//		System.out.println("Trainer is added with id:" + id);
//		
//		getTrainers().stream().forEach(e->{
//			System.out.println(e);
//		});
		Student student = getStudent(2001L);
		
		
	}
	
	
	private static void addBatchToTrainer(Trainer trainer, long id) {
		Session session = null;
		Transaction tx = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
		
		tx = session.beginTransaction();
		Batch batch = session.get(Batch.class, id);
		trainer.addBatch(batch);
		session.saveOrUpdate(trainer);
		tx.commit();
		session.close();
		
		
		
	}


	public static List<Trainer> getTrainers() {
		Session session = null;
		Transaction tx = null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		session = sf.openSession();
		tx = session.beginTransaction();
		List<Trainer> list = session.createQuery("from Trainer", Trainer.class).list();
		tx.commit();
		session.close();
		return list;
	}

	public static Long addTrainer(Trainer trainer) {
		Session session = null;
		Transaction tx = null;
		Long id = 0L;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			id = (Long) session.save(trainer);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null)
				session.close();
		}
		return id;
	}

	private static Student getStudent(Long id) {
		Student student = null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session session = sf.openSession();
			student = session.find(Student.class, id);
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
