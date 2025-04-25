package com.learning.jobms.job.clients;

import com.learning.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "companyms")
public interface CompanyClient {

    @GetMapping("/companies/{companyId}")
   Company getCompany(@PathVariable("companyId") Long companyId);
}
