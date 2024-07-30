package com.rubypaper.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.repository.EmployeeRepository;

@Service("empService")
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository empRepository;

	public void insertEmployee(Employee employee) {
		empRepository.save(employee);
	}

	public void updateEmployee(Employee employee) {
		empRepository.save(employee);
	}

	public void deleteEmployee(Employee employee) {
		empRepository.delete(employee);
	}

	public Employee getEmployee(Employee employee) {
		return empRepository.findById(employee.getId()).get();
	}
	
//	public List<Employee> getEmployeeList(Employee employee) {
//		//return (List<Employee>) empRepository.findByName(employee.getName()); // findAll() 에서 조건을 건 쿼리 메소드로 변환
//		//return (List<Employee>) empRepository.findByNameContaining(employee.getName());
//		//return (List<Employee>) empRepository.findByNameContainingOrMailIdContaining(employee.getName(),employee.getMailId());
//		//return (List<Employee>) empRepository.findByMailIdContainingOrderByNameDesc(employee.getMailId());
//	}
	
	public List<Employee> getEmployeeList(Employee employee, int pageNumber) {
		Pageable paging = PageRequest.of(pageNumber-1, 3, Sort.Direction.DESC, "id");
		return (List<Employee>)empRepository.findByNameContaining(employee.getName(), paging);
	}
	
}

/*
	기존 스프링 컨테이너와 엔터티 매니저가 따로따로 관리 되고 있을 때의 코드
	
	@Autowired
	private EmployeeRepository empRepository;

	public void insertEmployee(Employee employee) {
		empRepository.insertEmployee(employee);
	}

	public void updateEmployee(Employee employee) {
		empRepository.updateEmployee(employee);
	}

	public void deleteEmployee(Employee employee) {
		empRepository.deleteEmployee(employee);
	}

	public Employee getEmployee(Employee employee) {
		return empRepository.getEmployee(employee);
	}
	
	public List<Employee> getEmployeeList(Employee employee) {
		return empRepository.getEmployeeList(employee);
	}
*/
