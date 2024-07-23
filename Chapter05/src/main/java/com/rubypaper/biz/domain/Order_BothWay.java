package com.rubypaper.biz.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;


@Data
@Entity
@Table(name = "S_ORD")
public class Order_BothWay {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CUSTOMER_ID")
	private Long customerId;
	
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	
	private Double total;
	
	@ManyToMany(fetch = FetchType.EAGER) // 기본은 lazy
	@JoinTable(name = "S_ITEM",
				joinColumns = @JoinColumn(name = "ORD_ID"),
				inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"),
				uniqueConstraints = @UniqueConstraint(columnNames = {"ORD_ID", "PRODUCT_ID"})
				)
	private List<Product_BothWay> productList = new ArrayList<Product_BothWay>();
	
	// 상품을 등록할 때 따로 add() 하는 것이 아닌 메서드로 자동으로 하게 한다
	public void addProduct(Product_BothWay product) {
		productList.add(product);
		
		// 반대쪽(Product) 에도 주문에 대한 참조 정보 설정
		product.getOrderList().add(this);
	}
}
