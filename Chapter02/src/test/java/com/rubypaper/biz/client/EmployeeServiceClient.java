package com.rubypaper.biz.client;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee;


public class EmployeeServiceClient {
	public static void main(String[] args) {
		//entity manger factory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");
		
		// entity manger 생성
		EntityManager em = emf.createEntityManager();
		
		// entity transaction 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			// 직원 entity 생성
			Employee employee = new Employee();
			employee.setId(7L);
			employee.setName("둘리");
			employee.setMailId("gurum");
			employee.setStartDate(new Date());
			employee.setTitle("과장");
			employee.setDeptName("총무부");
			employee.setSalary(2500.00);
			employee.setCommisionPct(12.50);
			
			// 트랜잭션 시작
			tx.begin();
			
			// 직원 등록 요청
			em.persist(employee);
			
			// 트랜잭션 종료(commit)
			tx.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			
			// 트랜잭션 종료(rollback)
			tx.rollback();
		} finally {
			//entity manger & entity manger factory 종료
			em.close();
			emf.close();
		}
		
		
	}
}

	