package com.insuredoo.ipa.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import com.insuredoo.ipa.entities.Product;

public interface CsvUtil {
	public  <T> List<T> read(Class<T> clazz, File csvFile) throws IOException;
	
	public  List<Product> readProducts(File csvFile, HashSet<String> takafuls) throws IOException;

}
