package com.cts.portal.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDetails {

	private Long id;
	private String name;
	private String dob;      
	private String pandetails;
	private String email;
	private String phone;
	private String agentname;
	private Long agentid;
	private List<BusinessDetails> business;
}