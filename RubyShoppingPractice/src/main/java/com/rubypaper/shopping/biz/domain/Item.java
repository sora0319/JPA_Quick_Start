package com.rubypaper.shopping.biz.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ITEM")
public class Item{

	@Id @GeneratedValue
	private Long id;

	@Column(name = "COUNT")
	private int count;

	@JoinColumn(name = "itemList")
	@Column(name = "ORDER_ID")
	private Long orderId;

	@JoinColumn()
	@Column(name = "PRODUCT_ID")
	private Long productId;
}