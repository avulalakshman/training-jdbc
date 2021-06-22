package com.heraizen.hibernatecore.domain;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Embeddable
@ToString
public class Certification {

		private String name;
		private int year;
}
