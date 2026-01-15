package com.JobApplication.reviewms.reviews.dto;

import com.JobApplication.reviewms.clients.Companydto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAndReviewDTO {

    private ReviewDTO review;
    private Companydto Company;

    
}
