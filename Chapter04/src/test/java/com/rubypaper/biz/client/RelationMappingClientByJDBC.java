package com.rubypaper.biz.client;

import java.util.List;

import com.rubypapaer.biz.repository.EmployeeDAO;
import com.rubypaper.biz.domain.Employee;

public class RelationMappingClientByJDBC {
	public static void main(String[] args) {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employeeList = employeeDAO.getEmployeeList();
		
		for(Employee employee : employeeList) {
			System.out.println(employee.getName() + "직원의 부서명 : " + employee.getDept().getName());
		}
	}
}
