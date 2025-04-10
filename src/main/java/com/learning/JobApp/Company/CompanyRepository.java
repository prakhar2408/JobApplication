package com.learning.JobApp.Company;

import com.learning.JobApp.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    //<Entity, Primary key type>
}
