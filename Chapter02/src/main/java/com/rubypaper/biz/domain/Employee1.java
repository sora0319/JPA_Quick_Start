package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Employee1 {
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
