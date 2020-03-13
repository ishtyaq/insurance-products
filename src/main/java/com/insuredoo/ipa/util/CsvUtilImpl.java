package com.insuredoo.ipa.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.insuredoo.ipa.entities.Product;

@Component
public class CsvUtilImpl implements CsvUtil {

	@Override
	public <T> List<T> read(Class<T> clazz, InputStream csvFile) throws IOException {
		 CsvMapper mapper = new CsvMapper();
		 mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		 mapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES)
		 	.enable(CsvParser.Feature.TRIM_SPACES);
		 //	.enable(CsvParser.Feature.)
		 CsvSchema schema = mapper.schemaFor(clazz).withHeader().withoutQuoteChar();
	     ObjectReader reader = mapper.readerFor(clazz).with(schema);
	     return reader.<T>readValues(csvFile).readAll();
	}
	
	@Override
	public List<Product> readProducts(InputStream csvFile, HashSet<String> takafuls) throws IOException {
		 CsvMapper mapper = new CsvMapper();
		 mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

		 mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		 mapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES)
		 	.enable(CsvParser.Feature.TRIM_SPACES);
		 //	.enable(CsvParser.Feature.)
		 List<Product> result = new ArrayList<Product>();
		 
		 MappingIterator<String[]> it = mapper.readerFor(String[].class).readValues(csvFile);
		
		 try {
			int counter = -1;
			while (it.hasNext()) {
				String[] row = it.next();
				int length = row.length;
				// System.out.println(counter);
				counter += 1;
				if (counter == 0)
					continue;

				Product aProduct = new Product();

				if (row[0] != null) {
					if (takafuls.contains(row[0]))
						aProduct.setTakaful(1);
					else
						aProduct.setTakaful(0);

					aProduct.setCompanyName(row[0]);
				}

				aProduct.setProduct(row[1]);
				// System.out.println(row[2]);

				if (row[2] != null && !row[2].trim().isEmpty())
					aProduct.setAmount(Long.parseLong(row[2]));
				else
					aProduct.setAmount(0l);

				if (row[3] != null && !row[3].trim().isEmpty())
					aProduct.setModelFrom(Integer.parseInt(row[3]));
				else
					aProduct.setModelFrom(0);

				if (row[4] != null && !row[4].trim().isEmpty())
					aProduct.setModelTo(Integer.parseInt(row[4]));
				else
					aProduct.setModelTo(0);

				if (row[4] != null && !row[4].trim().isEmpty())
					aProduct.setModelTo(Integer.parseInt(row[4]));
				else
					aProduct.setModelTo(0);

				if (row[5] != null) {
					// System.out.println(row[5]);
					String brands = "";
					if (row[5].startsWith("\"")) {
						brands = row[5].replaceAll("\"", "");
						for (int i = 6; i < length; i++) {
							if (row[i].endsWith("\"")) {
								brands = brands + ", " + row[i].replaceAll("\"", "");
								break;
							} else
								brands = brands + ", " + row[i];

						}
					} else
						brands = row[5];
					aProduct.setBrandCoveraged(brands);
				}
				if (row[length - 1] != null && !row[length - 1].trim().isEmpty())
					aProduct.setTaxPercentage(Long.parseLong(row[length - 1]));
				else
					aProduct.setTaxPercentage(0l);

				result.add(aProduct);
				// and voila, column values in an array. Works with Lists as well

			} 
		} finally {
			it.close();
		}
		
		 
	     return result;
	}

}
