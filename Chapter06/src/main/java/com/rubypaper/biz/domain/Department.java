package com.rubypaper.biz.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "S_DEPT")
public class Department {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPT_ID")
	private Long deptId;

	private String name;

	@OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
	private List<EmployeeJoin> employeeList = new ArrayList<EmployeeJoin>();
}
