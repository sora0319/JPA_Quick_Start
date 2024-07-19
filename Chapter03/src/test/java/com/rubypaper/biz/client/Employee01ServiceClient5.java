package com.rubypaper.biz.client;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee01;

// entity 분리(준영속 상태) detach() -> entity 관리(영속 상태) 실습 merge()

public class Employee01ServiceClient5 {
	public static void main(String[] args) {
		
		// resources folder의 persistence.xml에 <persistence-unit name="Chapter02">참조
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
		
		EntityManager em = emf.createEntityManager();
		
		// 엔터티 트랜잭션 생성
		EntityTransaction tx = em.getTransaction();
		
		
		try {
			//Entity 만 생성된 상태
			//영속성 컨테이너에 등록이 되지 않은 상태
			Employee01 employee = new Employee01();
		
			//employee.setId(1L);
			employee.setName("홍길동");

			
			/*
			 	영속성 컨테이너에 엔터티 등록
			 	DB 에 저장 -> transaction 내에서 persist() 호출되어야 함
			 */
			// 트랜잭션 시작
			tx.begin();
			
			// 영속성 관리를 위한 엔터티 등록
			em.persist(employee);
			
			// 트랜잭션 종료
			tx.commit();
			
			// ========== 엔터티 분리  ===========
			if(em.contains(employee)) {
				System.out.println("영속성 컨테이너에 등록된 상태");
			}
			
			// 엔터티 분리
			em.detach(employee);
			if(!em.contains(employee)) {
				System.out.println("영속성 컨테이너에서 분리된 상태");
			}
			
			// ====== 엔터티 분리 -> 관리 변경 실습 
			employee = em.merge(employee);
			
			if(em.contains(employee)) {
				System.out.println("영속성 컨테이터에 등록된 상태");
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

	}

}
