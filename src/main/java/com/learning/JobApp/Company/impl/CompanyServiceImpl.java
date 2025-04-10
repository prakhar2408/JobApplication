package com.learning.JobApp.Company.impl;

import com.learning.JobApp.Company.Company;
import com.learning.JobApp.Company.CompanyRepository;
import com.learning.JobApp.Company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository CompanyRepository) {
        this.companyRepository = CompanyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company Company) {
        companyRepository.save(Company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company updateCompany(Long id, Company Company) {
        return companyRepository.findById(id).map(existingCompany -> {
            existingCompany.setName(Company.getName());
            existingCompany.setDescription(Company.getDescription());
            existingCompany.setJobs(Company.getJobs());
            return companyRepository.save(existingCompany);
        }).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
