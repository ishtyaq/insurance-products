package com.insuredoo.ipa.service;

import java.util.List;

import com.insuredoo.ipa.entities.Product;
import com.insuredoo.ipa.dto.ProductSearch;
import com.insuredoo.ipa.entities.Company;

public interface InsuranceService {
	
	public List<Product> getAllProducts(List<Company> listCompany);
	public List<Company> getAllCompanies();
	
	public List<Product> findBestPrice(List<Product> productList, Long price);
	
	public List<Product> findBestPriceByData(List<Company> companyList, ProductSearch productSearch);
	
}
