package com.rubypaper.biz.client;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee7;

public class Employee7ServiceClient {

	public static void main(String[] args) {
		
		// resources folder의 persistence.xml에 <persistence-unit name="Chapter02">참조
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");
		
		EntityManager em = emf.createEntityManager();
		
		// 엔터티 트랜잭션 생성
		EntityTransaction tx = em.getTransaction();
		
		
		try {
			// 영속성 관리 엔터티 생성
			
			
			/* lombok의 @Data를 삭제해 setter를 사용할 수 없는 상태이므로, 에러가 발생한다
				따라서 lombok 의 생성자를 사용하여 데이터를 저장해야 한다
			
			*/ 
			Employee7 employee = new Employee7(1L, "홍길동", "hong", new Date(), 
												"대리", "개발부", 2500.00, 12.5, 
												null, null);
		
//			employee.setId(1L);
//			employee.setName("홍길동");
//			employee.setMailId("hong1");
//			employee.setStartDate(new Date());
//			employee.setTitle("대리");
//			employee.setDeptName("개발부");
//			employee.setSalary(2500.00);
			// commissionPCT 의 허용 값 : 10, 12.5, 15, 17.5, 20
//			employee.setCommissionPct(12.5);
			
			// 트랜잭션 시작
			tx.begin();
			
			// 영속성 관리를 위한 엔터티 등록
			em.persist(employee);
			
			// 트랜잭션 종료
			tx.commit();
			
			// 등록된 사원 조회
			Employee7 findEmployee = em.find(Employee7.class, 1L);
			System.out.println("find 1L");
			System.out.println(findEmployee.toString());
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

	}

}
