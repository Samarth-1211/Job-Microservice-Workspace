package com.JobApplication.Jobms.job.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class jobDTO {

    private Long id;
    private String title;
    private String description;
    private Double minSalary;
    private Double maxSalary;
    private String location;
    private Long companyId ;
    
}
