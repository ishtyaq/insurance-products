package com.insuredoo.ipa.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	//Company,Product,Amount,Model From,Model To,Brands Covered,TAX Percentage
	@JsonProperty("Company")
	private String companyName;
	private String product;
	private Long amount;
	@JsonProperty("Model From")
	private Integer modelFrom;
	@JsonProperty("Model To")
	private Integer modelTo;
	@JsonProperty("Brands Covered")
	private String brandCoveraged;
	@JsonProperty("TAX Percentage")
	private Long taxPercentage;
	
	private Integer takaful;
	
	public Product() {
		
	}
	
	public Product(String companyName, String product, Long amount, Integer modelFrom, Integer modelTo,
			String brandCoveraged, Long taxPercentage) {
		super();
		this.companyName = companyName;
		this.product = product;
		this.amount = amount;
		this.modelFrom = modelFrom;
		this.modelTo = modelTo;
		this.brandCoveraged = brandCoveraged;
		this.taxPercentage = taxPercentage;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getModelFrom() {
		return modelFrom;
	}

	public void setModelFrom(Integer modelFrom) {
		this.modelFrom = modelFrom;
	}

	public Integer getModelTo() {
		return modelTo;
	}

	public void setModelTo(Integer modelTo) {
		this.modelTo = modelTo;
	}

	public String getBrandCoveraged() {
		return brandCoveraged;
	}

	public void setBrandCoveraged(String brandCoveraged) {
		this.brandCoveraged = brandCoveraged;
	}

	public Long getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(Long taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public Integer getTakaful() {
		return takaful;
	}

	public void setTakaful(Integer takaful) {
		this.takaful = takaful;
	}

	 
}
