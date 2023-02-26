package com.lidia.organization.services;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.repositories.DepartamentRepositoryJdbc;
import org.springframework.stereotype.Service;

@Service
public class DepartamentService {

    private final DepartamentRepositoryJdbc departamentRepository;

    public DepartamentService(DepartamentRepositoryJdbc departamentRepository) {
        this.departamentRepository = departamentRepository;
    }

    public void krijoDepartament(DepartamentDto departamentDto) {
        departamentRepository.krijoDepartament(departamentDto.getEmer());
    }

    public void ndryshoEmer(DepartamentDto departamentDto){
        departamentRepository.ndryshoEmer(departamentDto.getEmer(), departamentDto.getId());
    }

}
