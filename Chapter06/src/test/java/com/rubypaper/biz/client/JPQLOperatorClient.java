package com.rubypaper.biz.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.EmployeeJoin;

public class JPQLOperatorClient {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter06");
		try {
			insertData(emf);
			selectData(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}

	private static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();

		// is empty
		/*
		String jpql = "SELECT d FROM Department d " + 
			       "WHERE d.employeeList is empty";
		TypedQuery<Department> query = em.createQuery(jpql, Department.class);
		
		List<Department> resultList = query.getResultList();
		System.out.println("직원이 없는 부서 목록");
		for (Department department : resultList) {
			System.out.println(department.getName());
		}		
		*/
		
		// member of
		String jpql = "SELECT d FROM Department d " + 
			       "WHERE :employee member of d.employeeList";
		TypedQuery<Department> query = em.createQuery(jpql, Department.class);
		
		// 6번 직원 객체를 검색하여 파라미터로 설정한다.
		EmployeeJoin findEmp = em.find(EmployeeJoin.class, 6L);
		query.setParameter("employee", findEmp);
		
		List<Department> resultList = query.getResultList();
		System.out.println(findEmp.getId() + "번 직원이 소속되어있는 부서 목록");
		for (Department department : resultList) {
			System.out.println(department.getName());
		}		
		
		em.close();
	}


	private static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Department department1 = new Department();
		department1.setName("개발부");
		for (int i = 1; i <= 3; i++) {
			EmployeeJoin employee = new EmployeeJoin();
			employee.setName("개발직원 " + i);
			employee.setSalary(i * 12700.00);
			employee.setMailId("Dev-" + i);
			employee.setDept(department1);
		}
		em.persist(department1);

		Department department2 = new Department();
		department2.setName("영업부");
		for (int i = 1; i <= 3; i++) {
			EmployeeJoin employee = new EmployeeJoin();
			employee.setName("영업직원 " + i);
			employee.setSalary(27300.00 * i);
			employee.setMailId("Sale-" + i);
			employee.setDept(department2);
		}
		em.persist(department2);

		Department department3 = new Department();
		department3.setName("인재개발부");
		em.persist(department3);

		// 부서 정보가 없는 새로운 직원 추가
		Employee employee = new Employee();
		employee.setName("아르바이트");
		employee.setMailId("Alba-01");
		employee.setSalary(10000.00);
		em.persist(employee);

		// 이름이 영업부인 새로운 직원 추가
		Employee employee2 = new Employee();
		employee2.setName("영업부");
		em.persist(employee2);

		em.getTransaction().commit();
		em.close();
	}

}