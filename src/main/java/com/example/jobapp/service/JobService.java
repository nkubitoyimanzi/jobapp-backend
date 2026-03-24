package com.example.jobapp.service;
import com.example.jobapp.model.Job;
import com.example.jobapp.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class JobService {
    private final JobRepository jobRepository;
    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public List<Job> getJobs(){
        return jobRepository.findAll();

    }
    public Job getJobById(int id){
        return jobRepository.findById(id).orElse(null);
    }
    public Job updateJob(int id, Job updateJob) {
        Job existing = jobRepository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setTitle(updateJob.getTitle());
        existing.setDescription(updateJob.getDescription());
        return jobRepository.save(existing);

    }
        public Job createJob(Job job){
        return jobRepository.save(job);
    }

    public boolean deleteJob(int id){
        if(!jobRepository.existsById(id)){
            return false;
        }
        jobRepository.deleteById(id);
        return true;
    }

}
