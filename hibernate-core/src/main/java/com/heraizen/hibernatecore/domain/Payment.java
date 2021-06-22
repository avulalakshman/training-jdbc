package com.heraizen.hibernatecore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		private Long id;
		private String mode;
		private double amount;
		
		@ManyToOne(targetEntity = Student.class)
		@JoinColumn(name = "student_id",referencedColumnName = "id")
		private Student student;
		@ManyToOne(targetEntity = Student.class)
		@JoinColumn(name = "batch_id",referencedColumnName = "id")
		private  Batch batch;
}
