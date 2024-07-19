package com.rubypaper.biz.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude="employeeList")
@Entity
@Table(name = "S_DEPT")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPT_ID")
	private Long deptId;
	
	@Column(length = 25, nullable = false)
	private String name;
	
	/*	maapedBy : 연관관계 소유자 속성
	 	연관 관계 : s_emp, s_dept 테이블의 관계는 foreign key로 연결되어 있음
	 	dept는 Employee에 있는 부서의 아이디로 만든 foreign key 이다
	 	=>  mappedBy에 FK 관계 정보인 Employee의 dept 멤버로 설정
	 */
	@OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)
	private Set<Employee> employeeList = new HashSet<Employee>();
}
