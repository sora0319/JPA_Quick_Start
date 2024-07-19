package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/*
 	Chapter03 의 JPA 개발 및 테스트 환경 테스트 엔터티 클랫스
 	Chapter02에서 복사함
 */

@Data
@Entity
public class Employee_Test {
	@Id
	private Long id;
	
	private String name;
	
	private String mailId;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	private String title;
	
	@Column(name="DEPT_NAME")
	private String deptName;
	
	private Double salary;
	
	@Column(name="COMMISSION_PCT")
	private Double commissionPct;
}
