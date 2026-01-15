package com.JobApplication.reviewms.reviews.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobApplication.reviewms.reviews.entity.ReviewEntity;


@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity , Long>{

    List<ReviewEntity> findByCompanyId(Long companyId);
    // List<CompanyAndReviewDTO> findByCompanyAndReviewDTO(Long companyId);
    
}
                                                                    