package com.insuredoo.ipa.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insuredoo.ipa.entities.Product;
import com.insuredoo.ipa.service.InsuranceService;
import com.insuredoo.ipa.dto.ProductSearch;
import com.insuredoo.ipa.entities.Company;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {

	@Autowired
	InsuranceService insuranceService;
	
	
	
	@RequestMapping(value="/find")
	public String displaySearchForm(HttpServletRequest request, Model model) {
	//	Company aCompany = new Company();
		ProductSearch prodSearch = new ProductSearch();
		
		prodSearch.setDataSize(0);
		model.addAttribute("productSearch",prodSearch);
		//model.addAttribute("company",aCompany);
		
		

		
		try {
			//File file = ResourceUtils.getFile(csvFileName);
			  
			List<Company> listCompany = insuranceService.getAllCompanies();
			 
	
			System.out.println(listCompany.size());
			model.addAttribute("takafuls",listCompany);
			request.getSession().setAttribute("s-listTakaful",listCompany);
			//fileReader.
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		return "find-insurance";
	}
	
	@RequestMapping(value="/find", method=RequestMethod.POST)
	public String displaySearchResult(HttpServletRequest request,Model model, ProductSearch productSearch) {
		
		 
		//Company aTakaful = new Company();
		//Product aProduct = new Product();
		//System.out.println(price);
		
		
		//model.addAttribute("aTakaful",aCompany);
		
		System.out.println("Compnay" + productSearch.getPriceFrom());
		List<Company> listCompany=null;
		if(request.getSession().getAttribute("s-listTakaful")!=null) {
			listCompany = (List<Company>)request.getSession().getAttribute("s-listTakaful");
			model.addAttribute("takafuls",listCompany);
		}
		else
			model.addAttribute("takafuls",new ArrayList<Company>());
		
		 
		try {
			
			List<Product> productList = insuranceService.findBestPriceByData(listCompany, productSearch);
			
			productSearch.setDataSize(productList.size());
			
			System.out.println(productList.size());
			model.addAttribute("productList",productList);
			//fileReader.
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		model.addAttribute("productSearch",productSearch);
		return "find-insurance";
	}
}
