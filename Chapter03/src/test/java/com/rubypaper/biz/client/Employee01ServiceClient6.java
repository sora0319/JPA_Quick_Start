package com.rubypaper.biz.client;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee01;

// entity 분리(준영속 상태) -> entity 관리(영속 상태) 실습

public class Employee01ServiceClient6 {
	// resources folder의 persistence.xml에 <persistence-unit name="Chapter02">참조
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
	
	public static void main(String[] args) {
		// 영속 상태 엔터티 생성 후 준영속 상태로 전환된 값 전달
		Employee01 employee = createEntity("홍길동");
		
		// 준영속 상태인 엔터티에서 값 변경
		employee.setName("이름 수정");
		
		// 준영속 상태의 entity를 영속 상태로 변경
		mergeEntity(employee);
		
		emf.close();
	
			

	}
	private static Employee01 createEntity(String name) {
		// entity crate -> entity detach
		
		System.out.println("========= createEntity() start ===========");
		// Persistence context start
		EntityManager em1 = emf.createEntityManager();
		EntityTransaction tx1 = em1.getTransaction();
		
		//start transaction
		tx1.begin();
		
		// entity manage
		Employee01 employee = new Employee01();
		employee.setName(name);
		
		em1.persist(employee);
		
		// end transaction
		tx1.commit();
		
		// Persistence context 종료
		// 이때 종료를 하며 컨테이너 안의 엔터티들을 준영속상태로 변환후 컨테이너 종료
		em1.close();
		
		System.out.println("============ createEmployee() end ==============");
			
		// 준영속 상태 entity 반환
		return employee;
	}
	
	private static void mergeEntity(Employee01 employee) {
		// 분리 상태(준영속 상태) -> 관리상태(영속 상태)
		System.out.println("============== mergeEntity() start ============");
		
		// 새로운 Persistence context start
		EntityManager em2 = emf.createEntityManager();
		EntityTransaction tx2 = em2.getTransaction();
		
		tx2.begin();
		
		// merge
		// 이때 준영속 상태의 기존 엔터티와, 준영속 상태를 영속 상태로 바꾼 새로운 엔터티가 생성됨
		// 같은 데이터지만 두가지 버전이 존재하는 것
		Employee01 mergeEmployee = em2.merge(employee);
		
		tx2.commit();
		
		// 같은 내용 두가지 버전의 엔터티 비교
		System.out.println("employee(준영속 entity) : " + employee.toString());
		System.out.println("mergeEmployee(영속 entity) : " + mergeEmployee.toString());
		System.out.println("employee 의 상태 : " + em2.contains(employee));
		System.out.println("mergeEmployee 의 상태 : " + em2.contains(mergeEmployee));
		
		System.out.println(" ================ mergeEntity() end ===================");
	}

}
