package com.rubypaper.shopping.biz.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "S_CUSTOMER")
public class Customer{
    @Id @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private Long id;

    @Embedded
    private Address address;

    private String comments;

    private String name;

    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Order> orderList = new ArrayList<Order>();
}