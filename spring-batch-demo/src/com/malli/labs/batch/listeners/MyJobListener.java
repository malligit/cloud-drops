package com.malli.labs.batch.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.util.Date;

public class MyJobListener implements JobExecutionListener {
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Listener: Called before job @ "+ new Date());
    }

    public void afterJob(JobExecution jobExecution) {
        System.out.println("Listener: Called After job @ "+ new Date());
    }
}
