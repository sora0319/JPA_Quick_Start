package com.rubypaper.biz.client;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.rubypaper.biz.config.SpringConfiguration;
import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.service.DepartmentService;
import com.rubypaper.biz.service.EmployeeService;


public class QueryMethodClient {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		
		DepartmentService deptService = (DepartmentService) container.getBean("deptService");
		EmployeeService employeeService = (EmployeeService) container.getBean("empService");
		
		insertData(deptService, employeeService);
		selectData(employeeService);
		//selectData(deptService);
		container.close();
	}
	
	private static void selectData(DepartmentService deptService) {
		Department department = new Department();
		department.setDeptId(1L);
		Department findDept = deptService.getDepartment(department);
		
		System.out.println("부서명 : " + findDept.getName());
		System.out.println("직원 목록");
		
		for(Employee employee : findDept.getEmployeeList()) {
			System.out.println("---> " + employee.toString());
		}
	}
	
	private static void selectData(EmployeeService employeeService) {
		Employee employee = new Employee();
		employee.setName("");	
		employee.setMailId("Dev");
		List<Employee> resultList = employeeService.getEmployeeList(employee,1);
		
		System.out.println("직원 목록");
		for (Employee result : resultList) {
			System.out.println("--> " + result.toString());
		}
	}
	
	
	private static void insertData(DepartmentService deptService, EmployeeService employeeService) {
		Department department1 = new Department();
		department1.setName("개발부");
		deptService.insertDepartment(department1);
		
		Department department2 = new Department();
		department2.setName("영업부");
		deptService.insertDepartment(department2);
		
		for(int i = 1; i <= 5; i++) {
			Employee employee = new Employee();
			employee.setName("개발직원 " + i);
			employee.setSalary(i * 12700.00);
			employee.setMailId("Dev-" + i);
			employee.setDept(department1);
			employeeService.insertEmployee(employee);
		}
		
		for(int i = 1; i <= 7; i++) {
			Employee employee = new Employee();
			employee.setName("영업직원 " + i);
			employee.setSalary(i * 24300.00);
			employee.setMailId("Sales-" + i);
			employee.setDept(department2);
			employeeService.insertEmployee(employee);
		}
	}

}
