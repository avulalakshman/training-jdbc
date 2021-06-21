package com.lwl.tjdbc.util;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class EmployeeDTO {

	private int empno;
	private String ename;
	private String job;
	private double salary;
	private String dname;
	private String location;
	private int grade;

}
