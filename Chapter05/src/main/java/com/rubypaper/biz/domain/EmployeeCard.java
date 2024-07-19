package com.rubypaper.biz.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "S_EMP_CARD")
public class EmployeeCard {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CARD_ID")
	private Long cardId;
	
	@Column(name = "EXPIRE_DATE")
	private Date expireDate;
	
	private String role;
	
	@OneToOne
	@JoinColumn(name = "EMP_CARD_ID")
	private Employee employee;
}
