package com.rubypaper.biz.client;

// 엔터티 조회  : 엔터티 매니저에 존재하지 않은 엔터티 조회

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee01;

public class Employee01ServiceClient2 {

	public static void main(String[] args) {
		
		// resources folder의 persistence.xml에 <persistence-unit name="Chapter02">참조
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
		
		EntityManager em = emf.createEntityManager();
		
		// 엔터티 트랜잭션 생성
		//EntityTransaction tx = em.getTransaction();
		
		
		try {
			/*
			 * 이 실습시 <property name="hibernate.hbm2ddl.auto" value="update"/> 로 update 변경하고 실시
			 * 
			 * ServiceClient 를 create로 실행하여 table생성후 이 코드를 실행
			 * 
			 * entity manger가 조회를 하였을 때 우리는 단지 find() 만 사용하였지만 
			 * 이 thread에 있는 entity manger는 1차 캐시에 데이터가 저장되어 있지 않기 때문에 
			 * DB에 select로 데이터 값을 들고와서 결과를 보여주게 된다
			 */
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
