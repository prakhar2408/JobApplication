package com.learning.companyms.Company;

import java.util.List;

public interface CompanyService {

    public List<Company> findAll();

    public void createCompany(Company Company);

    public Company updateCompany(Long id, Company Company);

    public Company getCompanyById(Long id);

    public boolean deleteCompanyById(Long id);
}
