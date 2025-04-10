package com.learning.JobApp.job;

import com.learning.JobApp.Company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
    //<Entity, Primary key type>
}
