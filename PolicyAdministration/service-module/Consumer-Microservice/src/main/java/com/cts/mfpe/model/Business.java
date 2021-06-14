package com.cts.mfpe.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="business")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Business {
	@Id
	@Column(name ="ID")
	private Long id;
	
	@NotBlank
	@Size(max = 40)
	@Column(name = "Business_Category")
	private String businesscategory;

	@NotBlank
	@Size(max = 40)
	@Column(name = "Business_Type")
	private String businesstype;

	@NotNull
	@Column(name = "Business_Turnover")
	private Long businessturnover;

	@NotNull
	@Column(name = "Capital_Invested ")
	private Long capitalinvested;

	@NotNull
	@Column(name = "Total_Employees")
	private Long totalemployees;

	@NotNull
	@Column(name = "Business_Value")
	private Long businessvalue;

	@NotNull
	@Column(name = "Business_Age")
	private Long businessage;
	
//	private List<Property> properties;
	
	
}
