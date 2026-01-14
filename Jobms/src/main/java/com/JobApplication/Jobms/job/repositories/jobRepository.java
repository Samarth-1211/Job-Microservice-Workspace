package com.JobApplication.Jobms.job.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobApplication.Jobms.job.entity.jobEntity;


@Repository
public interface jobRepository extends JpaRepository<jobEntity , Long>  {
    
}
