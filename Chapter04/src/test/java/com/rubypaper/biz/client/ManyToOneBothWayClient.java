package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;

public class ManyToOneBothWayClient {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		try {
			insertData(emf);
			//dataSelect(emf);
			//dataUpdate(emf);
			//dataDelete(emf);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
		

	}
	private static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// 부서 등록
		Department department = new Department();
		department.setName("회계부");
		em.persist(department);
		
		//직원 등록
		Employee employee1 = new Employee();
		employee1.setName("둘리2");
		employee1.setDept(department);
		em.persist(employee1);
		
		// 직원 등록2
		Employee employee2 = new Employee();
		employee2.setName("도우너2");
		employee2.setDept(department);
		em.persist(employee2);
		
		
		
		//따로 등록하지 않고 참조시 바로 등록이 되도록 수정
//		department.getEmployeeList().add(employee1);
//		department.getEmployeeList().add(employee2);
		
		System.out.println(department.getName() + "의 직원 수 : " + department.getEmployeeList().size());
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		Employee employee = em.find(Employee.class, 1L);
		//System.out.println(employee.getName());
		System.out.println(employee.getName() + "의 부서 : " + employee.getDept().getName());
		/*
		 	실행결과 select 문으로 결과 값을 들고와서 출력
		 	select문에서 두 테이블을 left outer join을 해서 값을 생성 후 엔터티에 할당
		 	=> left outer join을 하는 이유 : 
		 		직원 중 부서에 할당되지 않은 사람이 있을 수 있으므로 inner join을 하면 부서가 없는 직원은 값을 찾지 못한다
		 		left outer join을 해서 모든 직원의 정보에서 필요한 직원의 정보를 찾는다
		 	그러나, 필요에 따라서는 inner join을 사용해야 할 때가 있으므로 inner join으로 할 수 있게 기능을 주고 있다
		 	@ManyToOne(optional = false)
		 */
		em.close();
	}
	
	private static void updateData(EntityManagerFactory emf) {
		
	}
	
	private static void deleteData(EntityManagerFactory emf) {

	}

}
