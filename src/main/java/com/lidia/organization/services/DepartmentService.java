package com.lidia.organization.services;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.repositories.DepartamentRepositoryJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartamentRepositoryJdbc departamentRepository;

    public DepartmentService(DepartamentRepositoryJdbc departamentRepository) {
        this.departamentRepository = departamentRepository;
    }

    public void krijoDepartament(DepartamentDto dto) {
        departamentRepository.krijoDepartament(dto.getEmer());
    }

}
