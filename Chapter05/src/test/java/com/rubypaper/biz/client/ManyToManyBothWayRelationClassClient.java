package com.rubypaper.biz.client;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Item;
import com.rubypaper.biz.domain.Order;
import com.rubypaper.biz.domain.OrderNonIdentifying;
import com.rubypaper.biz.domain.ProductNonIdentifying;

public class ManyToManyBothWayRelationClassClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter05");
		try {		
			insertData(emf);
			selectData(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}
	
	private static void selectData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		
		OrderNonIdentifying order = em.find(OrderNonIdentifying.class, 1L);
		System.out.println("주문 날짜 : " + order.getOrderDate());
		System.out.println("[ 주문 목록 ]");
		List<Item> itemList = order.getItemList();
		for (Item item : itemList) {
			System.out.println("---> " + item.getProduct().getName());
		}
		
//		ProductNonIdentifying product = em.find(ProductNonIdentifying.class, 1L);
//		System.out.println("[ 주문 목록 ]");
//		List<Item> itemList2 = product.getItemList();
//		for (Item item : itemList2) {
//			System.out.println("---> " + item.getProduct().getName());
//		}
	}
	
	private static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();		
		
		// 상품 등록
		ProductNonIdentifying product1 = new ProductNonIdentifying();
		product1.setName("LG 통돌이 세탁기");
		em.persist(product1);

		ProductNonIdentifying product2 = new ProductNonIdentifying();
		product2.setName("갤럭시 20");
		em.persist(product2);
		
		
		// 주문 등록
		OrderNonIdentifying order = new OrderNonIdentifying();
		order.setOrderDate(new Date());
		em.persist(order);
				
		// 카트 등록
		Item item1 = new Item();
		item1.setOrder(order);
		item1.setProduct(product1);
		item1.setPrice(100000L);
		item1.setQuantity(2L);
		em.persist(item1);
		
		Item item2 = new Item();
		item2.setOrder(order);
		item2.setProduct(product2);
		item2.setPrice(270000L);
		item2.setQuantity(3L);
		em.persist(item2);

		em.getTransaction().commit();
		em.close();
	}
}
