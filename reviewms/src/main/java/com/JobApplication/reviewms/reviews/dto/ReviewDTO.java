package com.JobApplication.reviewms.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private Long id;
    private String title;
    private String description;
    private Double ratings;
    private Long companyId;

}
