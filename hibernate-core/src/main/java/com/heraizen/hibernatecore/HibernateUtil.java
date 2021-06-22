package com.heraizen.hibernatecore;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.heraizen.hibernatecore.domain.Batch;
import com.heraizen.hibernatecore.domain.Passport;
import com.heraizen.hibernatecore.domain.Payment;
import com.heraizen.hibernatecore.domain.Student;
import com.heraizen.hibernatecore.domain.Trainer;



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
						settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/sample");
						settings.put(Environment.USER, "lakshman");
						settings.put(Environment.PASS, "lakshman@123");

						settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL94Dialect");

						settings.put(Environment.SHOW_SQL, "true");

						settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

						settings.put(Environment.HBM2DDL_AUTO, "create");

						configuration.setProperties(settings);
					
						configuration.addAnnotatedClass(Student.class);
						configuration.addAnnotatedClass(Passport.class);
						configuration.addAnnotatedClass(Trainer.class);
						configuration.addAnnotatedClass(Batch.class);
						configuration.addAnnotatedClass(Payment.class);
						
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