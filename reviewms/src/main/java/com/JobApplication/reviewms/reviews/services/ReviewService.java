package com.JobApplication.reviewms.reviews.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.JobApplication.reviewms.exceptions.ResourceNotFoundException;
import com.JobApplication.reviewms.reviews.dto.ReviewDTO;
import com.JobApplication.reviewms.reviews.entity.ReviewEntity;
import com.JobApplication.reviewms.reviews.repositories.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repo;
    private final ModelMapper mapper;
   
    public List<ReviewDTO> getAllReview(Long companyId) {

        List<ReviewEntity> reviewsEntity = repo.findByCompanyId(companyId);
        return reviewsEntity
                .stream()
                .map(reviewEntity -> mapper.map(reviewEntity, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    public ReviewDTO PostReviewForCompany(ReviewDTO review, Long companyId) {
        if(companyId != null && review != null){
            ReviewEntity entity = mapper.map(review, ReviewEntity.class);
            entity.setCompanyId(companyId);
            return mapper.map(repo.save(entity), ReviewDTO.class);    
        }else throw new NullPointerException("Review Or Comapny Id is Null");
    }




    public void deleteReviewOfCompanyByReviewId(Long reviewId) {
      repo.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review With Particular Id Does Not Exist"));
        repo.deleteById(reviewId);
    }




    public ReviewDTO getReviewByreviewId(Long reviewId) {
        ReviewEntity entity = repo.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review With Prticular Id does Not Exist"));
        return mapper.map(entity, ReviewDTO.class);
    }



    public ReviewDTO updateReviewofACopmanyWithReviewId(Long reviewId, ReviewDTO ReviewUpdate) {

        ReviewEntity existing = repo.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review With Id -> " + reviewId + " Does Not Exist"));

            existing.setTitle(ReviewUpdate.getTitle());
            existing.setDescription(ReviewUpdate.getDescription());
            existing.setRatings(ReviewUpdate.getRatings());
            
        return mapper.map(repo.save(existing), ReviewDTO.class);
    }

}
