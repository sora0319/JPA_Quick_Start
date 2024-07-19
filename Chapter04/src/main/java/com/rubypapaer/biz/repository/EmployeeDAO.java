package com.rubypapaer.biz.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;

public class EmployeeDAO {
	// JDBC APIs
		private Connection conn = null;
		private PreparedStatement stmt = null;
		private ResultSet rs = null;
		
		public List<Employee> getEmployeeList(){
			List<Employee> employeeList = new ArrayList<Employee>();
			try {
				conn = getConnection();
				stmt = conn.prepareStatement(
					    "SELECT e.id, e.name, d.dept_id, d.name dname " +
					    "FROM s_emp e, s_dept d " +
					    "WHERE e.dept_id = d.dept_id " +
					    "ORDER BY e.id ASC");

				rs = stmt.executeQuery();
				while(rs.next()){
					Employee employee = new Employee();
					employee.setId(rs.getLong("ID"));
					employee.setName(rs.getString("NAME"));
					
					Department department = new Department();
					department.setDeptId(rs.getLong("DEPT_ID"));
					department.setName(rs.getString("DNAME"));
					
					employee.setDept(department);
					employeeList.add(employee);
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				close(rs,stmt, conn);
			}
			return employeeList;
		}
		
		// 커넥션 획득
		public Connection getConnection() {
			try {
				Class.forName("org.h2.Driver");	
				String url = "jdbc:h2:tcp://localhost/./test";
				conn = DriverManager.getConnection(url, "sa", "sa");	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		// 커넥션 종료(ResultSet, Statement, Connection)
		public void close(ResultSet rs, Statement stmt, Connection conn) {
			try {
				if(rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
			
			try {
				if(stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
			
			try {
				if(conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
		
		// 커넥션 종료(Statement, Connection)
		public void close(Statement stmt, Connection conn) {
			try {
				if(stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
			
			try {
				if(conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}

}
