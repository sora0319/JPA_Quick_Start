package com.rubypaper.shopping.biz.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

public class OrderService{
    @Autowired
    EntityManager em;



}