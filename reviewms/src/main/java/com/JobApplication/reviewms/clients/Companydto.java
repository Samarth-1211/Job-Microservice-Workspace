package com.JobApplication.reviewms.clients;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Companydto {
    
    private Long id;
    private String name;
    private String description;
    private List<Long> jobId;
    private List<Long> reviewId;


}
