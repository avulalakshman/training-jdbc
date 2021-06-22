package com.heraizen.hibernatecore.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
		private String email;
		private String mobile;
}
