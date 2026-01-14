package com.JobApplication.Jobms.job.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.JobApplication.Jobms.job.DTO.jobDTO;
import com.JobApplication.Jobms.job.entity.jobEntity;
import com.JobApplication.Jobms.job.repositories.jobRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class jobService {
    private final jobRepository repo;
    private final ModelMapper mapper;

    public List<jobDTO> getAllJobs() {
       

        List<jobEntity> entities = repo.findAll();

        return entities
                .stream() //iterate 
                .map(jobentity -> mapper.map(jobentity, jobDTO.class))
                .collect(Collectors.toList());

    }

    public jobDTO createnewJobPost(jobDTO jobdata) {
        jobEntity entity = mapper.map(jobdata, jobEntity.class);
        return mapper.map(repo.save(entity), jobDTO.class);
    }

    public jobDTO getJobById(Long jobId) {

        return mapper.map(repo.findById(jobId).orElse(null), jobDTO.class);

    }

    public boolean deletejobById(Long jobId) {
        try {
            if (repo.findById(jobId) != null) {
                repo.deleteById(jobId);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public jobDTO updateJobById(jobDTO dto, Long jobId) {

        if (repo.findById(jobId) == null) {
            return null;
        }
        jobEntity entity = mapper.map(dto, jobEntity.class);
        entity.setId(jobId);
        return mapper.map(repo.save(entity), jobDTO.class);

    }

}
