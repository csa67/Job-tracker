package com.app.job_tracker.controller;

import com.app.job_tracker.entity.JobRecord;
import com.app.job_tracker.model.JobRecordDto;
import com.app.job_tracker.service.JobsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
@Validated
public class JobsController {

    @Autowired
    private JobsService jobService;


    @PostMapping("/add")
    public ResponseEntity<?> addJobRecord(@Valid  @RequestBody JobRecordDto jobRecordDto) {
        return ResponseEntity.ok(jobService.addJobRecord(jobRecordDto));
    }
}
