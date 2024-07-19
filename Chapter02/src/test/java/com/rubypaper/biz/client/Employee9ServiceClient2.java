package com.rubypaper.biz.client;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee9;


/*
 	식별자 identity 의 값 생성 순서
 	
 	
 	
 */
public class Employee9ServiceClient2 {

	public static void main(String[] args) {
		
		// resources folder의 persistence.xml에 <persistence-unit name="Chapter02">참조
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");
		
		EntityManager em = emf.createEntityManager();
		
		// 엔터티 트랜잭션 생성
		EntityTransaction tx = em.getTransaction();
		
		
		try {
			
		
			//employee.setId(1L);
			//employee.setName("홍길동2");

			
			// 트랜잭션 시작
			tx.begin();
			
			//System.out.println("등록 전 id : " + employee.getId());
			
			// 영속성 관리를 위한 엔터티 등록
			//em.persist(employee);
			
			// 트랜잭션 커밋하기 까지 30초 라는 시간 동안
			// select 한 결과를 확인
			for(int i = 0; i < 100; i++) {
				Employee9 employee = new Employee9();
				employee.setName("홍길동" + i);
				em.persist(employee);
			}
			
			// 데이터베이스가 id 칼럼에 증가된 식별자 값을 할당
			
			
			// 클라이언트 쪽에도 JPA 가 관리하는 employee 객체에도 증가된 식별자 값이 할당
			//System.out.println("등록  후 id : " + employee.getId());
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
