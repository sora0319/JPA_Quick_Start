package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Department_OneToMany;
import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.Employee_OneToMany;

public class OneToManyOneWayClient {

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
		Department_OneToMany department = new Department_OneToMany();
		department.setName("회계부");
		em.persist(department);
		
		//직원 등록
		Employee_OneToMany employee1 = new Employee_OneToMany();
		employee1.setName("둘리2");
		em.persist(employee1);
		
		// 직원 등록2
		Employee_OneToMany employee2 = new Employee_OneToMany();
		employee2.setName("도우너2");
		em.persist(employee2);

		department.getEmployeeList().add(employee1);
		department.getEmployeeList().add(employee2);
		
		System.out.println(department.getName() + "의 직원 수 : " + department.getEmployeeList().size());
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		Employee employee = em.find(Employee.class, 1L);
		
		em.close();
	}
	
	private static void updateData(EntityManagerFactory emf) {
		
	}
	
	private static void deleteData(EntityManagerFactory emf) {

	}

}
