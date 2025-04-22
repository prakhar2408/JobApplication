package com.learning.companyms.Company;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

//    private List<Long> jobs;
//    private List<Long> reviews;
//
//    public List<Long> getJobs() {
//        return jobs;
//    }
//
//    public void setJobs(List<Long> jobs) {
//        this.jobs = jobs;
//    }
//
//    public List<Long> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Long> reviews) {
//        this.reviews = reviews;
//    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Company(){
        // JPA requires no Args constructors
    }

}
