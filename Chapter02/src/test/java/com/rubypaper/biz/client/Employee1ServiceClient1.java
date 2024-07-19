package com.rubypaper.biz.client;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee1;

public class Employee1ServiceClient1 {

	public static void main(String[] args) {
		
		// resources folder의 persistence.xml에 <persistence-unit name="Chapter02">참조
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");
		
		EntityManager em = emf.createEntityManager();
		
		try {
			// 영속성 관리 엔터티 생성
			Employee1 employee = new Employee1();
			
			employee.setId(1L);
			employee.setName("홍길동");
			employee.setMailId("hong");
			employee.setStartDate(new Date());
			employee.setTitle("대리");
			employee.setDeptName("개발부");
			employee.setSalary(2500.00);
			employee.setCommissionPct(12.50);
			
			// 영속성 관리를 위한 엔터티 등록
			em.persist(employee);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

	}

}
