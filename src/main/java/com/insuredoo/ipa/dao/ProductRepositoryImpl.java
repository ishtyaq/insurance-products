package com.insuredoo.ipa.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.insuredoo.ipa.entities.Product;
import com.insuredoo.ipa.util.CsvUtil;

@Component
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	CsvUtil csvUtil;
	
	@Override
	public List<Product> getAllProducts(InputStream file, HashSet<String> takafuls) throws IOException {
		List<Product>  productList = csvUtil.readProducts(file, takafuls);
		return productList;
		
	}

}
