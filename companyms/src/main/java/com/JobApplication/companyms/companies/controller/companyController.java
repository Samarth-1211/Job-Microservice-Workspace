package com.JobApplication.companyms.companies.controller;

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

import com.JobApplication.companyms.companies.dto.companydto;
import com.JobApplication.companyms.companies.services.companyService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping(path = "companies")
public class companyController {

    private final companyService service;


    @GetMapping
    public ResponseEntity<List<companydto>> getAllCompanies(){
        return ResponseEntity.ok(service.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<companydto> createCompany(@RequestBody companydto dto){
        return new ResponseEntity<>(service.createCompany(dto),HttpStatus.CREATED);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long companyId){
        String response = service.deleteCompanyById(companyId); 
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<companydto> getCompanyById(@PathVariable Long companyId){
        companydto dto = service.getCompanyById(companyId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<companydto> updateCompanyById(@RequestBody companydto updatedto , @PathVariable Long companyId){
        companydto updateCompanydto = service.updateCompanyById(updatedto , companyId);
        return new ResponseEntity<>(updateCompanydto , HttpStatus.OK);
    }
    
}
