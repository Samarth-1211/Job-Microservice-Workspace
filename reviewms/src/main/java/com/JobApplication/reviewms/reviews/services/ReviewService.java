package com.JobApplication.reviewms.reviews.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.JobApplication.reviewms.clients.Companydto;
import com.JobApplication.reviewms.exceptions.ResourceNotFoundException;
import com.JobApplication.reviewms.reviews.dto.CompanyAndReviewDTO;
import com.JobApplication.reviewms.reviews.dto.ReviewDTO;
import com.JobApplication.reviewms.reviews.entity.ReviewEntity;
import com.JobApplication.reviewms.reviews.repositories.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repo;
    private final ModelMapper mapper;

    
    @Qualifier("companymsRestClient")
    private final RestClient restClient;



    // Usefull Methods 
    //1
    public Companydto getCompanydto(Long companyId){
        return  restClient.get()
        .uri("/{companyId}", companyId)
        .retrieve()
        .body(Companydto.class);
    }

    //2

    public CompanyAndReviewDTO convertReviewdtoCompanyAndReviewDTO(ReviewEntity reviewEntity ,Long companyId){
       
            CompanyAndReviewDTO compAndRevDTO = new CompanyAndReviewDTO();
            
            compAndRevDTO.setReview(mapper.map(reviewEntity,ReviewDTO.class));
            Companydto company = getCompanydto(companyId);
            compAndRevDTO.setCompany(company);
        return compAndRevDTO;
    }



    public List<CompanyAndReviewDTO> getAllReview(Long companyId) {
        List<ReviewEntity> reviewsEntity = repo.findByCompanyId(companyId);
        // 1. Validate company existence via CompanyMS
        Companydto company = getCompanydto(companyId);
        if (company == null || company.getId() == null) {
            throw new ResourceNotFoundException(
                    "Company with id " + companyId + " does not exist");
        }
        // Set All the reviewEntity to companyAndReviewDTO
        return reviewsEntity.stream()
                                .map(
                                    entity -> 
                                        convertReviewdtoCompanyAndReviewDTO(entity ,companyId))
                                .collect(Collectors.toList());
    }

    public ReviewDTO PostReviewForCompany(ReviewDTO review, Long companyId) {
        if (companyId != null && review != null) {
            ReviewEntity entity = mapper.map(review, ReviewEntity.class);
            // entity.setCompanyId(companyId);
            entity.setCompanyId(getCompanydto(companyId).getId());
            return mapper.map(repo.save(entity), ReviewDTO.class);
        } else
            throw new NullPointerException("Review Or Comapny Id is Null");
    }

    public void deleteReviewOfCompanyByReviewId(Long reviewId) {
        repo.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review With Particular Id Does Not Exist"));
        repo.deleteById(reviewId);
    }

    public ReviewDTO getReviewByreviewId(Long reviewId) {
        ReviewEntity entity = repo.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review With Prticular Id does Not Exist"));
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
