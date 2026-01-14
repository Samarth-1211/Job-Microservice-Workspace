package com.JobApplication.Jobms.job.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobApplication.Jobms.job.DTO.jobDTO;
import com.JobApplication.Jobms.job.services.jobService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
@RequestMapping(path = "/jobs")  //C R(All , Specific ) U D --> GET /POST / PUT / DELETE /PATCH
public class jobController{

    private final jobService service;

    @GetMapping
    public ResponseEntity<List<jobDTO>> getAllJobs(){
        return ResponseEntity.ok(service.getAllJobs());
    }

    // @RequestBody , @RequestParam , @PathVariable 

    @PostMapping
    public ResponseEntity<jobDTO> createnewJobPost(@RequestBody jobDTO jobdata){
        return new ResponseEntity<>(service.createnewJobPost(jobdata),HttpStatus.CREATED);
    }

    @GetMapping("/{jobId}") 
    public ResponseEntity<jobDTO> getJobById(@PathVariable Long jobId){
        return new ResponseEntity<>(service.getJobById(jobId),HttpStatus.FOUND);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long jobId){
         if(service.deletejobById(jobId)){
            return  ResponseEntity.ok("Job Deleted Successfully with Id  " + jobId);
         }

          return ResponseEntity
         .status(HttpStatus.NOT_FOUND)
         .body("Job Id " + jobId + "not found");
    }

        @PutMapping("{jobId}")
        public ResponseEntity<jobDTO> updateJobById(@RequestBody jobDTO dto ,@PathVariable Long jobId){
           return new ResponseEntity<>(service.updateJobById(dto,jobId),HttpStatus.OK);


        }
        

}
