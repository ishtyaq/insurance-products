package com.insuredoo.ipa.dto;

public class ProductSearch {
	private Long priceFrom;
	private Long priceTo;
	private String companyName;
	private Integer dataSize;
	
	public Long getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(Long priceFrom) {
		this.priceFrom = priceFrom;
	}
	public Long getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(Long priceTo) {
		this.priceTo = priceTo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getDataSize() {
		return dataSize;
	}
	public void setDataSize(Integer dataSize) {
		this.dataSize = dataSize;
	}

}
