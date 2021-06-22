package com.heraizen.hibernatecore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Student implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String name;
	private String mobile;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "passport_id")
	private Passport passport;

	@ManyToMany
	@JoinTable(name = "student_batch", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "batch_id"))
	private List<Batch> batches=new ArrayList<Batch>();

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
	private List<Payment> payments=new ArrayList<Payment>();

	public void addBatch(Batch batch) {
		this.batches.add(batch);

	}

	public void removeBatch(Batch batch) {
		this.batches.remove(batch);
	}

	public void addPayment(Payment payment) {
		this.payments.add(payment);
		payment.setStudent(this);
	}

	public void removePayment(Payment payment) {
		this.payments.remove(payment);
		payment.setStudent(null);
	}
}
