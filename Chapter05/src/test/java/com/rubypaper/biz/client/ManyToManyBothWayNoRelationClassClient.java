package com.rubypaper.biz.client;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Order;
import com.rubypaper.biz.domain.Order_BothWay;
import com.rubypaper.biz.domain.Product;
import com.rubypaper.biz.domain.Product_BothWay;

public class ManyToManyBothWayNoRelationClassClient {

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
		Order_BothWay order_BothWay = em.find(Order_BothWay.class, 1L);
		System.out.println(order_BothWay.getId() + "번 주문에 대한 상품 목록");
		List<Product_BothWay> productList = order_BothWay.getProductList();
		for(Product_BothWay prBWay : productList) {
			System.out.println("---> " + prBWay.getName());
		}
		
		// 검색한 Product_BothWay를 통해 Order 목록을 출력
		Product_BothWay product_BothWay = em.find(Product_BothWay.class, 1L);
		
		System.out.println(product_BothWay.getName() + "상품에 대한 주문 정보");
		List<Order_BothWay> orderList = product_BothWay.getOrderList();
		for(Order_BothWay ordBWay: orderList) {
			System.out.println("-->" + ordBWay.toString());
		}
		
		em.close();
	}
	
	private static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// 1번 상품 등록
		Product_BothWay product1 = new Product_BothWay();
		product1.setName("LG 통돌이 세탁기");
		em.persist(product1);
		
		// 2번 상품 등록
		Product_BothWay product2 = new Product_BothWay();
		product2.setName("다이슨 청소기");
		em.persist(product2);
		
		// 1번 주문 등록
		Order_BothWay order1 = new Order_BothWay();
		order1.setOrderDate(new Date());
		order1.addProduct(product1);
		em.persist(order1);
		
		
		// 2번 주문 등록
		Order_BothWay order2 = new Order_BothWay();
		order2.setOrderDate(new Date());
		order2.addProduct(product1);
		em.persist(order2);
		
		em.getTransaction().commit();
		em.close();
	}

}
