package com.JobApplication.companyms.companies.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class companydto {
    
    private Long id;
    private String name;
    private String description;
    private List<Long> jobId;
    private List<Long> reviewId;


}
