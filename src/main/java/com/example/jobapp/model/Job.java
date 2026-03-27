package com.example.jobapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;


    public Job() {
    }


    public Job(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public Job(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }
}