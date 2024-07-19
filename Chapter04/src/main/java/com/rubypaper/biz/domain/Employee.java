package com.rubypaper.biz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "S_EMP")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 25, nullable = false)
	private String name;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER) // N: 1 관계 (N : 사원, 1 : 부서)
	@JoinColumn(name = "DEPT_ID")
	private Department dept;
	
	// 부서 정보를 직원에서 참조를 할때 부서도 같이 참조되는 직원을 등록을 해서 자연스럽게 양방향 참조 등록이 완료 된다
	public void setDept(Department department) {
		this.dept = department;
		department.getEmployeeList().add(this);
	}
}
