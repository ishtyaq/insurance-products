package com.insuredoo.ipa;


import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import com.insuredoo.ipa.dto.ProductSearch;
import com.insuredoo.ipa.entities.Company;
import com.insuredoo.ipa.entities.Product;
import com.insuredoo.ipa.service.InsuranceService;


@SpringBootTest
class InsuranceProductsApplicationTests {

	@Autowired
	InsuranceService insuranceService;
	
	@Test
	@DisplayName("Load Takafal List, must Pass!")
	void testLoadTakafuls() {
		List<Company> list = insuranceService.getAllCompanies();
		//System.out.println(list.size());
		assertFalse(list.size()==0, "Size: " + list.size());
		
	}
	@Test
	@DisplayName("Load Product List, must Pass!")
	void testLoadProducts() {
		List<Product> list = insuranceService.getAllProducts(null);
		//System.out.println(list.size());
		assertTrue(list.size()>0, "Size: " + list.size());
		
	}
	@Test
	@DisplayName("Check Takaful status loaded in product(1 or 0) > 0, must Pass!")
	void testProductsTakafulStatus() {
		List<Product> list = insuranceService.getAllProducts(null);
		//System.out.println(list.size());
		boolean statusLoaded=false;
		for(Product product: list) {
			if(product.getTakaful()== 1 || product.getTakaful()==0 ) {
				statusLoaded = true;
				break;
			}
				
		}
		if(!statusLoaded)
			fail("Takful Status not loaded");
		
		assertFalse(!statusLoaded);
		
	}
	
	@Test
	@DisplayName("Search Best Price & Takaful (442,company h), must Pass!")
	void testBestPrice() {
		ProductSearch productSearch = new ProductSearch();
		productSearch.setPriceFrom(442l);
		productSearch.setPriceTo(442l);
		//productSearch.setPriceFrom(442l);
		List<Product> list = insuranceService.findBestPriceByData(null, productSearch);
		if(list.size()==2) {
			if(!list.get(0).getCompanyName().equalsIgnoreCase("company h"))
				fail("Not loading takaful company");
		}
		else {
			fail("Not all records loaded." + list.size());
		}
		//System.out.println(list.size());
		assertTrue(list.size()==2, "Size: " + list.size());
		
	}

	@Test
	@DisplayName("Search product for unknown company, must pass!")
	void testPriceByUnknownCompany() {
		ProductSearch productSearch = new ProductSearch();
		 
		productSearch.setCompanyName("Apple");
		List<Product> list = insuranceService.findBestPriceByData(null, productSearch);
		if(list.size()>0) {
			 
			fail("Search Failed");
		}
		 
		//System.out.println(list.size());
		assertFalse(list.size()>0, "Size: " + list.size());
		
	}
	@Test
	@DisplayName("Search all products without any condition, must pass!")
	void testPriceByAll() {
		ProductSearch productSearch = new ProductSearch();
		 
		//productSearch.setPriceFrom(54000l);
		List<Product> list = insuranceService.findBestPriceByData(null, productSearch);
		if(list.size()!=9) {
			 
			fail("Search Failed to return all records: " + list.size());
		}
		 
		//System.out.println(list.size());
		assertFalse(list.size()!=9, "Size: " + list.size());
		
	}
	@Test
	@DisplayName("Search product for High Price, must pass!")
	void testPriceByHighPrice() {
		ProductSearch productSearch = new ProductSearch();
		 
		productSearch.setPriceFrom(54000l);
		List<Product> list = insuranceService.findBestPriceByData(null, productSearch);
		if(list.size()>0) {
			 
			fail("Search Failed for price: " + productSearch.getPriceFrom());
		}
		 
		//System.out.println(list.size());
		assertFalse(list.size()>0, "Size: " + list.size());
		
	}
	@Test
	@DisplayName("Search products for null data, must fail!")
	void testPriceByNullData() {
		assertThrows(NullPointerException.class,
	            ()->{insuranceService.findBestPriceByData(null, null);} );
		
	}
	
	@Test
	@DisplayName("Search products by providing wrong price, must fail!")
	void testPriceByWrongData() {
		assertThrows(IllegalArgumentException.class,
	            ()->{insuranceService.findBestPrice(null, Long.parseLong("ab"));} );
		
	}
	@Test
	@DisplayName("Load Product List for 10k records, must Pass!")
	void testPerformance10K() {
		
		
		assertTimeout(Duration.ofSeconds(10),() -> {
			for(int i=1; i<=1000; i++) {
				insuranceService.getAllProducts(null);
			}
		});
		//System.out.println(list.size());
		
		
	}
}
