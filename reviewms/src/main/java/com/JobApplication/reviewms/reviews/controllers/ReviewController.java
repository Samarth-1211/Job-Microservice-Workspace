package com.JobApplication.reviewms.reviews.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JobApplication.reviewms.reviews.dto.ReviewDTO;
import com.JobApplication.reviewms.reviews.services.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path ="reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;


    @GetMapping()
    public ResponseEntity<List<ReviewDTO>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(service.getAllReview(companyId),HttpStatus.OK);
    }

    @GetMapping(path = "/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewByreviewId(@PathVariable Long reviewId){
        return new ResponseEntity<>(service.getReviewByreviewId(reviewId),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ReviewDTO> PostReviewForCompany(@RequestBody ReviewDTO review , @RequestParam Long companyId ){
        ReviewDTO reviewcreated = service.PostReviewForCompany(review ,companyId);
        return new ResponseEntity<>(reviewcreated,HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{reviewId}")
    public ResponseEntity<String> deleteReviewOfCompanyByReviewId(@PathVariable Long reviewId){
        service.deleteReviewOfCompanyByReviewId(reviewId);
        return new ResponseEntity<>("Review with Id " + reviewId + " DeletedSuccessfully", HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDTO> updateReviewofACopmanyWithReviewId(@PathVariable Long reviewId , @RequestBody ReviewDTO ReviewUpdate){
        ReviewDTO updatedReview = service.updateReviewofACopmanyWithReviewId(reviewId , ReviewUpdate);
        return new ResponseEntity<>(updatedReview,HttpStatus.OK);
    }
 

    
}
