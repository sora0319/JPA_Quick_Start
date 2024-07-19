package com.rubypaper.persistence.jdbc;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeVO {
	private Long id;
	private String name;
	private Timestamp startDate;
	private String title;
	private String deptName;
	private Double salary;
	
	

}
