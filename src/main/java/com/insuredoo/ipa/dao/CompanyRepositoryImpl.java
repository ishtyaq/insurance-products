package com.insuredoo.ipa.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.insuredoo.ipa.entities.Company;
import com.insuredoo.ipa.util.CsvUtil;

@Component
public class CompanyRepositoryImpl implements CompanyRepository {

	@Autowired
	CsvUtil csvUtil;
	
	@Override
	public List<Company> getAllCompanies(File file) throws IOException {
		List<Company> listCompany  = csvUtil.read(Company.class, file);
		return listCompany;
	}

}
