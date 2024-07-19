package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/*
 * @Temporal
 * 
 * 날짜 데이터 : 날짜 정보 + 시간정보
 * 			- 날짜 정보
 * 			- 시간정보
 * 			- 날짜 정보 + 시간정보
 */

@Data
@Entity
@Table(name = "Employee5", uniqueConstraints = {@UniqueConstraint
		(columnNames = {"NAME","MAILID"})})
public class Employee5 {
	@Id
	@Column(length = 7, nullable = false)
	private Long id;
	
	@Column(length = 25, nullable = false)
	private String name;
	
	@Column(length = 8, unique = true)
	private String mailId;
	
	@Column(name="START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(length = 25)
	private String title;
	
	@Column(name="DEPT_NAME", length = 30)
	private String deptName;
	
	@Column(precision = 11, scale = 2)
	private Double salary;
	
	// java 언어로 Double형을 설정해 놓았기 때문에 percision, scale 이 무시가 된다
	//@Column(name="COMMISSION_PCT", precision = 2, scale = 1)
	// 테이블에 한번 생성 된후 제약조건이 추가가 되면 적용이 되지 않는다 (삭제 후 다시 생성)
	@Column(name="COMMISSION_PCT", precision = 2, scale = 1,
			columnDefinition = "double CHECK (COMMISSION_PCT in (10,12.5,15,17.5,20))")
	private Double commissionPct;
}
