package com.rubypaper.biz.client;

// 엔터티 삭제 상태 실습

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee01;

public class Employee01ServiceClient4 {

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
			
			// ========== 엔터티 삭제 실습 시작 ===========
			Employee01 findEmp = em.find(Employee01.class, 1L);
			System.out.println(findEmp.toString());
			
			tx.begin();
			em.remove(findEmp);
			tx.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

	}

}