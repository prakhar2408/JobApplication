package com.learning.JobApp.job.impl;

import com.learning.JobApp.job.Job;
import com.learning.JobApp.job.JobRepository;
import com.learning.JobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job updateJob(Long id, Job job) {
        return jobRepository.findById(id).map(existingJob -> {
            existingJob.setTitle(job.getTitle());
            existingJob.setDescription(job.getDescription());
            existingJob.setMinSalary(job.getMinSalary());
            existingJob.setMaxSalary(job.getMaxSalary());
            return jobRepository.save(existingJob);
        }).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
