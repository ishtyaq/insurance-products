package com.insuredoo.ipa.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {

	@JsonProperty("Company")
	private String companyName;
	private String islamic;
	
	public Company() {
		
	}
	public Company(String companyName, String islamic) {
		super();
		this.companyName = companyName;
		this.islamic = islamic;
	}
	
	public String getIslamic() {
		return islamic;
	}
	public void setIslamic(String islamic) {
		this.islamic = islamic;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	 
}
