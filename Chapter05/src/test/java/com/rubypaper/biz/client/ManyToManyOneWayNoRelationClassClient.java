package com.rubypaper.biz.client;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Order;
import com.rubypaper.biz.domain.Product;

public class ManyToManyOneWayNoRelationClassClient {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter05");
		
		try {
			insertData(emf);
			selectData(emf);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}
	
	private static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		// order를 검색하여 order와 연관된 product 목록 출력
		Order order = em.find(Order.class, 1L);
		System.out.println(order.getId() + "번 주문에 대한 상품 목록");
		
		List<Product> productList = order.getProductList();
		for(Product product : productList) {
			System.out.println("---> " + product.getName());
		}
		
		em.close();
	}
	
	private static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// 1번 상품 등록
		Product product1 = new Product();
		product1.setName("LG 통돌이 세탁기");
		em.persist(product1);
		
		// 2번 상품 등록
		Product product2 = new Product();
		product2.setName("다이슨 청소기");
		em.persist(product2);
		
		// 1번 주문 등록
		Order order = new Order();
		order.setOrderDate(new Date());
		// 주문 객체의 productList에 상품을 저장
		order.getProductList().add(product1);
		order.getProductList().add(product2);
		em.persist(order);
		
		
		em.getTransaction().commit();
		em.close();
	}

}
