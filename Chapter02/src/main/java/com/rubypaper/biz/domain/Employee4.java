package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/*
 @Column 에 columnDefinition 속성 추가함
 
 Double 에 대한 precision, scale 설정 테스트
 precision : 숫자 관련(소수점 포함) 데이터 전체 길이
 	scale	: 소수점 데이터의 길이
 */

@Data
@Entity
@Table(name = "Employee4", uniqueConstraints = {@UniqueConstraint
		(columnNames = {"NAME","MAILID"})})
public class Employee4 {
	@Id
	@Column(length = 7, nullable = false)
	private Long id;
	
	@Column(length = 25, nullable = false)
	private String name;
	
	@Column(length = 8, unique = true)
	private String mailId;
	
	@Column(name="START_DATE", insertable = false)
	private Date startDate;
	
	@Column(length = 25)
	private String title;
	
	@Column(name="DEPT_NAME", length = 30)
	private String deptName;
	
	@Column(precision = 11, scale = 2)
	private Double salary;
	
	// java 언어로 Double형을 설정해 놓았기 때문에 percision, scale 이 무시가 된다
	//@Column(name="COMMISSION_PCT", precision = 2, scale = 1)
	// 테이블에 한번 생성 된후 제약조건이 추가가 되면 적용이 되지 않는다
	@Column(name="COMMISSION_PCT", precision = 2, scale = 1,
			columnDefinition = "double CHECK (COMMISSION_PCT in (10,12.5,15,17.5,20))")
	private Double commissionPct;
}
