package com.insuredoo.ipa.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.insuredoo.ipa.entities.Product;
import com.insuredoo.ipa.dao.CompanyRepository;
import com.insuredoo.ipa.dao.ProductRepository;
import com.insuredoo.ipa.dto.ProductSearch;
import com.insuredoo.ipa.entities.Company;
import com.insuredoo.ipa.util.CsvUtil;

@Service
public class InsuranceServicempl implements InsuranceService {

	
	@Autowired
	ProductRepository prodRepository;
	
	@Autowired
	CompanyRepository compRepository;
	
	@Autowired
	CsvUtil csvUtil;
	
	@Override
	public List<Product> getAllProducts(List<Company> listCompany) {
		//File file;
		List<Product> productList=null;
		try {
			
			//ClassPathResource resource = new ClassPathResource("data/Products.csv");
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("data/Products.csv");

			if(listCompany==null) {
				listCompany=this.getAllCompanies();
			}
			
			
			// store takafuls in array to store along with main data, for quicker result in search
			HashSet<String> takafuls = new HashSet<String>();
			
			for(Company company: listCompany) {
				if(company.getIslamic().equalsIgnoreCase("yes"))
					takafuls.add(company.getCompanyName());
			}
			productList = prodRepository.getAllProducts(in, takafuls);

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return productList;
	}

	@Override
	public List<Company> getAllCompanies() {
		//File file;
		List<Company> listCompany=null;
		try {

			InputStream in = this.getClass().getClassLoader().getResourceAsStream("data/Takaful.csv");

			listCompany = csvUtil.read(Company.class, in); // compRepository.getAllCompanies(file);
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//InputStream in = new FileInputStream(file);
		return listCompany;
	}

	@Override
	public List<Product> findBestPrice(List<Product> productList, Long price) {
		
		//Compare by price and then by takafal
        Comparator<Product> compareByPrice = Comparator
                                                .comparingLong(Product::getAmount)
                                                .thenComparing(Product::getTakaful);
        
		List<Product> productResult = productList.parallelStream()
				.filter(line -> price.compareTo(line.getAmount())>=0)
				.sorted(compareByPrice)
                .collect(Collectors.toList());
        return productResult;
	}

	@Override
	public List<Product> findBestPriceByData(List<Company> companyList, ProductSearch productSearch) {
		
		if(productSearch==null)
			throw new NullPointerException();
		// get all products
		List<Product> productList = this.getAllProducts(companyList);
		
		//Compare by price and then by takafal
        Comparator<Product> compareByPrice = Comparator
                                                .comparingLong(Product::getAmount)
                                               .thenComparing(Product::getTakaful,Comparator.reverseOrder());
        Stream<Product> prodStream = productList.parallelStream();
        
       
        System.out.println(productSearch.getPriceTo());
          //compareByPrice = compareByPrice.thenComparing(Comparator.comparing(product -> product.));
		List<Product> productResult = null;
        
        List<Predicate<Product>> allPredicates = new ArrayList<Predicate<Product>>();
        if(productSearch.getPriceFrom()!=null) {
            Predicate<Product> predicate1  = line -> line.getAmount().compareTo(productSearch.getPriceFrom())>=0;
        	allPredicates.add(predicate1);
        }
        if(productSearch.getPriceTo()!=null){
            Predicate<Product> predicate2  = line -> line.getAmount().compareTo(productSearch.getPriceTo()) <=0;
            allPredicates.add(predicate2);
        }
        if(productSearch.getCompanyName()!=null && !productSearch.getCompanyName().trim().equals("0")){
            Predicate<Product> predicate3  = line -> line.getCompanyName().contains(productSearch.getCompanyName());
            allPredicates.add(predicate3);
        }
        System.out.println("Size Predicate: " + allPredicates.size());
        productResult = productList.parallelStream()
			.filter(allPredicates.stream().reduce(x->true, Predicate::and))
			.sorted(compareByPrice).limit(15)
	        .collect(Collectors.toList());
         
        return productResult;
	}
}
