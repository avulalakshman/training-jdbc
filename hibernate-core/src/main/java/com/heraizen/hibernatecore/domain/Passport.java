package com.heraizen.hibernatecore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Passport {
	
		@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String passportNumber;

	@OneToOne(mappedBy = "passport")
	private Student student;
}
