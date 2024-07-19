package com.rubypaper.biz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

/*
	식별자 (Identity)
	
	JPA 가 관리하는 엔터티는 @Id 로 지정한 식별자 변수를 통해서 식별이 됨
	테이블의 기본키와 에넡티의 식별자 변수를 
	
	p.118 참고
	
	Sequence를 사용한 실습
	
 */

@Data
@Entity
@Table(name = "Employee10")
@TableGenerator(name = "SEQ_GENERATOR", // generator 이름
				table = "SHOPPING_SEQENCE", // table 이름
				pkColumnName = "SEQ_NAME", // table column 이름
				pkColumnValue = "EMP_SEQ", // SEQ_NAME 칼럼의 값
				valueColumnName = "NEXT_VALUE", // table column 이름
				initialValue = 0,
				allocationSize = 1)
public class Employee10 {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_GENERATOR")
	private Long id;
	
	private String name;
}
