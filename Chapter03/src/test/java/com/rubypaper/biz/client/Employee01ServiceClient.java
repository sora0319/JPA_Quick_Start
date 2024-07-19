package com.rubypaper.biz.client;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee01;

public class Employee01ServiceClient {

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
			
			/* ======== Entity 수정
			 	결과적으로 update 문장이 h2 db에 전송
			 	
			 	Dirty checking(변경 감지)
			 	영속성 컨테이너는 관리중인 엔터니의 변경이 되는 순간,
			 	변경을 감지하여 데이터베이스에 update 문장을 전송
			*/
			tx.begin();
			employee.setName("이름 수정");
			tx.commit();
			
			/**
				DB 변경 사항을 반영하려면 transaction 내부에서 수행해야 함
			 */
			employee.setName("이름수정2");
			
			// ============= 영속성 컨테이너 등록, Entity Manger의 find()
			Employee01 findEmp = em.find(Employee01.class, 1L);
			System.out.println(findEmp.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

	}

}
