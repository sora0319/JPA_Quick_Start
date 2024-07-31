package com.rubypaper.shopping.biz.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "ORDER")
public class Order{
	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	@Column(name = "DATE")
	private Date orderDate;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@OneToMany(mappedBy = "orderId" )
	private List<Item> itemList = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
}

