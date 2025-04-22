package com.learning.companyms.Company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    //<Entity, Primary key type>
}
