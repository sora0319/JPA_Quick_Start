package com.rubypaper.biz.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.rubypaper.biz.domain.Employee;

public class JPQLBasicClient {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("Chapter06");
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

		// JPQL
		String jpql = "SELECT id, name, deptName, salary FROM Employee";
		
		// JPQL 전송
		Query query = em.createQuery(jpql);
		List<Object[]> resultList = query.getResultList();
		
		// 검색 결과 처리
		System.out.println("검색된 직원 목록");
		for(Object[] result : resultList) {
			System.out.println("---> " + Arrays.toString(result));
		}

		em.close();
	}

	private static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// 10 명의 직원 정보 등록
		for (int i = 1; i <= 10; i++) {
			Employee employee = new Employee();
			employee.setName("직원 " + i);
			employee.setMailId("anti-corona" + i);
			employee.setDeptName("개발부");
			employee.setSalary(12700.00 * i);
			employee.setStartDate(new Date());
			employee.setTitle("사원");
			employee.setCommissionPct(15.00);
			em.persist(employee);
		}

		em.getTransaction().commit();
		em.close();
	}
}
