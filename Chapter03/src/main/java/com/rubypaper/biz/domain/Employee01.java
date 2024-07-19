package com.rubypaper.biz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/*
	식별자 (Identity)
	
	JPA 가 관리하는 엔터티는 @Id 로 지정한 식별자 변수를 통해서 식별이 됨
	테이블의 기본키와 에넡티의 식별자 변수를 
	
	p.118 참고
	
 */

@Data
@Entity
@Table(name = "S_EMP")
public class Employee01 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
}
