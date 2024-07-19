package com.rubypaper.biz.client;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee8;

public class Employee8ServiceClient {

	public static void main(String[] args) {
		
		// resources folder의 persistence.xml에 <persistence-unit name="Chapter02">참조
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");
		
		EntityManager em = emf.createEntityManager();
		
		// 엔터티 트랜잭션 생성
		EntityTransaction tx = em.getTransaction();
		
		
		try {
			Employee8 employee = new Employee8();
		
			//employee.setId(1L);
			employee.setName("홍길동");

			
			// 트랜잭션 시작
			tx.begin();
			
			// 영속성 관리를 위한 엔터티 등록
			em.persist(employee);
			
			// 트랜잭션 종료
			tx.commit();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

	}

}
