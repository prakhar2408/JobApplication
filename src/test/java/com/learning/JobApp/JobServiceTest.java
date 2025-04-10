package com.learning.JobApp;

import com.learning.JobApp.job.Job;
import com.learning.JobApp.job.JobRepository;
import com.learning.JobApp.job.impl.JobServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    @Test
    public void testFindAllJobs() {
        List<Job> jobs = List.of(new Job("1000", 1L, "Developer", "Writes code", "2000", "Remote"));
        when(jobRepository.findAll()).thenReturn(jobs);

        List<Job> result = jobService.findAll();
        assertEquals(1, result.size());
        assertEquals("Developer", result.get(0).getTitle());
    }

    @Test
    public void testCreateJob() {
        Job job = new Job("1000", 1L, "Developer", "Writes code", "2000", "Remote");
        when(jobRepository.save(job)).thenReturn(job);

        jobService.createJob(job);
        verify(jobRepository, times(1)).save(job);
    }

    @Test
    public void testUpdateJob() {
        Job existingJob = new Job("1000", 1L, "Developer", "Writes code", "2000", "Remote");
        Job updatedJob = new Job("1000", 1L, "Senior Developer", "Writes better code", "3000", "Remote");

        when(jobRepository.findById(1L)).thenReturn(Optional.of(existingJob));
        when(jobRepository.save(existingJob)).thenReturn(updatedJob);

        Job result = jobService.updateJob(1L, updatedJob);
        assertNotNull(result);
        assertEquals("Senior Developer", result.getTitle());
        assertEquals("Writes better code", result.getDescription());
    }

    @Test
    public void testGetJobById() {
        Job job = new Job("1000", 1L, "Developer", "Writes code", "2000", "Remote");
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        Job result = jobService.getJobById(1L);
        assertNotNull(result);
        assertEquals("Developer", result.getTitle());
    }

    @Test
    public void testGetJobById_NotFound() {
        when(jobRepository.findById(1L)).thenReturn(Optional.empty());

        Job result = jobService.getJobById(1L);
        assertNull(result);
    }

    @Test
    public void testDeleteJobById() {
        doNothing().when(jobRepository).deleteById(1L);

        boolean result = jobService.deleteJobById(1L);
        assertTrue(result);
        verify(jobRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteJobById_NotFound() {
        doThrow(new RuntimeException("Job not found")).when(jobRepository).deleteById(1L);

        boolean result = jobService.deleteJobById(1L);
        assertFalse(result);
    }


}