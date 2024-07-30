package com.rubypaper.biz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.rubypaper.biz.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findByName(String name); // query method사용
	
	List<Employee> findByNameContaining(String name, Pageable paging);
	
	List<Employee> findByNameContainingOrMailIdContaining(String name, String mailId);
	
	List<Employee> findByMailIdContainingOrderByNameDesc(String mailId);
	
}
