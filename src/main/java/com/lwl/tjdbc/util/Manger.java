package com.lwl.tjdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Manger {

	private static final String DEPT_DETAILS = "select deptno,dname, loc from dept";
	private static final String EMP_GRADE_DEPT_DETAILS = "select e.empno as empno,e.ename as ename,e.job as job,e.sal as salary,s.grade as grade,d.dname as dname,d.loc as location from emp e inner join dept d on e.deptno=d.deptno inner join salgrade s on e.sal between s.losal and s.hisal and d.deptno=?;";

	public static void main(String[] args) {
		showDeptDetails();

		int deptno = 10;
		List<EmployeeDTO> empDtoList = getEmployeeWithGadeDeptDetails(deptno);
		empDtoList.stream().forEach(System.out::println);
	}

	private static List<EmployeeDTO> getEmployeeWithGadeDeptDetails(int deptno) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		ConnectionUtil util = ConnectionUtil.obj;
		List<EmployeeDTO> list = new ArrayList<>();

		try {
			con = util.getConnection();
			st = con.prepareStatement(EMP_GRADE_DEPT_DETAILS);
			st.setInt(1, deptno);
			rs = st.executeQuery();
			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				double salary = rs.getDouble("salary");
				String dname = rs.getString("dname");
				String location = rs.getString("location");
				int grade = rs.getInt("grade");
				String job = rs.getString("job");
				EmployeeDTO emp = EmployeeDTO.builder()
							.empno(empno)
							.ename(ename)
							.salary(grade)
							.location(location)
							.dname(dname)
							.grade(grade)
							.job(job)
							.salary(salary)
							.build();
				list.add(emp);
			}
		} catch (SQLException e) {
			System.out.println("While getting dept dept details :" + e);
		} finally {
			util.close(rs, st, con);
		}
		return list;
	}

	private static void showDeptDetails() {
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		ConnectionUtil util = ConnectionUtil.obj;

		try {
			con = util.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(DEPT_DETAILS);
			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				System.out.println(String.format("%s %20s %20s", deptno, dname, loc));
			}
		} catch (SQLException e) {
			System.out.println("While getting dept dept details :" + e);
		} finally {
			util.close(rs, st, con);
		}

	}

}
