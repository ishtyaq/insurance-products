package com.insuredoo.ipa.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

import com.insuredoo.ipa.entities.Product;

public interface CsvUtil {
	public  <T> List<T> read(Class<T> clazz, InputStream csvFile) throws IOException;
	
	public  List<Product> readProducts(InputStream csvFile, HashSet<String> takafuls) throws IOException;

}
