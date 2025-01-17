package com.rubypaper.biz.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rubypaper.biz.domain.Department;

@Repository
public class DepartmentRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertDepartment(Department department) {
		System.out.println("==> JPA 로 insertDepartment() 기능 처리");
		em.persist(department);
	}
	
	public Department getDepartment(Department department) {
		System.out.println("==> JPA 로 getDepartment() 기능 처리");
		return em.find(Department.class, department.getDeptId());
	}
}
