package com.example.jobapp.controller;

import com.example.jobapp.model.Job;
import com.example.jobapp.repository.JobRepository;
import com.example.jobapp.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jobs")
public class JobController {


    private final JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping
    public List<Job> getJobs() {
        return jobService.getJobs();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable int id) {
        Job job = jobService.getJobById(id);

        if (job == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(job);
    }
    @PutMapping("{id}")
    public Job updateJob(@PathVariable int id, @RequestBody Job job){
        return jobService.updateJob(id, job);
    }


    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteJob( @PathVariable int id){
        boolean deleted = jobService.deleteJob(id);
        if(!deleted){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();

    }

}