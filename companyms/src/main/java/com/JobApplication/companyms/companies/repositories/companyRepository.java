package com.JobApplication.companyms.companies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobApplication.companyms.companies.entity.companyEntity;

@Repository
public interface companyRepository extends JpaRepository<companyEntity , Long>{
    
}
