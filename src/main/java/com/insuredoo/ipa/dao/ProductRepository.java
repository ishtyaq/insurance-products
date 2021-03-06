package com.insuredoo.ipa.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

import com.insuredoo.ipa.entities.Product;

public interface ProductRepository {
	public List<Product> getAllProducts(InputStream file, HashSet<String> takafuls) throws IOException;

}
