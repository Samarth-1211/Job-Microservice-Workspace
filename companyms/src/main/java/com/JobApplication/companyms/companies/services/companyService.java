package com.JobApplication.companyms.companies.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.JobApplication.companyms.companies.dto.companydto;
import com.JobApplication.companyms.companies.entity.companyEntity;
import com.JobApplication.companyms.companies.repositories.companyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class companyService {

    private final companyRepository repo;
    private final ModelMapper mapper;

    public List<companydto> getAllCompanies() {

        List<companyEntity> entities = repo.findAll();

        return entities
                .stream()
                .map(companyEntity -> mapper.map(companyEntity, companydto.class))
                .collect(Collectors.toList());
    }

    public companydto createCompany(companydto dto) {
        return mapper.map(repo.save(mapper.map(dto, companyEntity.class)), companydto.class);
    }

    public String deleteCompanyById(Long companyId) {
        if (repo.findById(companyId) == null) {
            return "Company With id" + companyId + "Does Not Exist";
        }
        repo.deleteById(companyId);
        return "Job With Id " + companyId + "Deleted Successfully";
    }

    public companydto getCompanyById(Long companyId) {
        companyEntity entity = repo.findById(companyId).orElse(null);
        return mapper.map(entity, companydto.class);
    }


    public companydto updateCompanyById(companydto updatedto, Long companyId) {
        if (!repo.findById(companyId).isPresent()) {
            return null;
        }
        companyEntity entity = mapper.map(updatedto, companyEntity.class);
        entity.setId(companyId);
        entity.setJobId(updatedto.getJobId());
        entity.setJobId(updatedto.getJobId());
        repo.save(entity);
        return mapper.map(entity, companydto.class);
    }

}
