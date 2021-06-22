package com.heraizen.hibernatecore.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Batch {
	
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private Long id;
		private String name;
		private double fee;
		
		@ManyToOne
		@JoinColumn(name = "trainer_id",nullable = false)
		private Trainer trainer;
		
		@ManyToMany(mappedBy = "batches")
		private List<Student> students;
		
		@OneToMany(mappedBy = "batch",fetch = FetchType.EAGER)
		private List<Payment> payments;
		
		public void addStudent(Student student) {
			this.students.add(student);
			student.addBatch(this);
		}
		public void removeStudent(Student student) {
			this.students.remove(student);
			student.addBatch(null);
		}
		public void addPayment(Payment payment) {
			this.payments.add(payment);
			payment.setBatch(this);
		}
		public void removePayment(Payment payment) {
			this.payments.add(payment);
			payment.setBatch(null);
		}
}
