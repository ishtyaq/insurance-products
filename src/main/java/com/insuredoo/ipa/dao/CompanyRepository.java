package com.insuredoo.ipa.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.insuredoo.ipa.entities.Company;

public interface CompanyRepository {
	public List<Company> getAllCompanies(InputStream file) throws IOException ;
}
