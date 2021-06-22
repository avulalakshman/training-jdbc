package com.heraizen.hibernatecore;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.heraizen.hibernatecore.domain.Department;
import com.heraizen.hibernatecore.domain.Student;

public final class HibernateUtil {

	private static SessionFactory sessionFactory;

	private HibernateUtil() {

	}

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			synchronized (HibernateUtil.class) {
				if (sessionFactory == null) {
					try {
						Configuration configuration = new Configuration();
						Properties settings = new Properties();
						settings.put(Environment.DRIVER, "org.postgresql.Driver");
						settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/heraizen");
						settings.put(Environment.USER, "lakshman");
						settings.put(Environment.PASS, "lakshman@123");

						settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL94Dialect");

						settings.put(Environment.SHOW_SQL, "true");

						settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

						settings.put(Environment.HBM2DDL_AUTO, "update");

						configuration.setProperties(settings);
					
			
						configuration.addAnnotatedClass(Department.class).addAnnotatedClass(Student.class);
						
						ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
								.applySettings(configuration.getProperties()).build();

						sessionFactory = configuration.buildSessionFactory(serviceRegistry);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
		return sessionFactory;
	}
}