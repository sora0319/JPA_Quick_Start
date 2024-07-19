package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/*
 * @Table에 uniqueConstraints 속성 추가
 * NAME, MAILED 두 칼럼을 대상으로 unique 제약 조건 생성
 * 
 * 1. 테이블에 uniqueConstraints 생성유무 확인
 * 		NAME, MAILID 의 두개의 칼럼으로 복합 unique 제약조건이 생성되었는지 확인
 * 2. uniqueConstraints 동작 테스트
 * 3. 테스트 데이터 생성 
 * 		hong, 홍길동 : unique 제약 조건 위배
 * 		hong2, 홍길동 : insert 성공
 * 		hong, 홍길동3 : insert 성공
 * 		hong4, 홍길동4 : insert 성공
 * 
 * 그래서, 정말 중요한 것은 생성되는 데이터가 처음부터 깨끗하게 만들어지지 않는다면, 
 * 향후에 개발중에서 이상한 데이터가 나오게 됨.
 * ( 들어가는 것이 깨끗해야 나오는 것도 깨끗함. )
 */

@Data
@Entity
@Table(name = "Employee3", uniqueConstraints = {@UniqueConstraint
		(columnNames = {"NAME","MAILID"})})
public class Employee3 {
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
