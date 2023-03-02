package com.lidia.organization.services.impl;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.repositories.DepartamentJdbcRepository;
import com.lidia.organization.services.api.DepartamentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentServiceImpl implements DepartamentService {

    private final DepartamentJdbcRepository departamentRepository;

    public DepartamentServiceImpl(DepartamentJdbcRepository departamentRepository) {
        this.departamentRepository = departamentRepository;
    }

    @Override
    public void krijoDepartament(DepartamentDto departamentDto) {
        departamentRepository.krijoDepartament(departamentDto.getEmer());
    }

    @Override
    public List<DepartamentDto> lexoDepartamentet() {
        return departamentRepository.lexoDepartamentet();
    }

    @Override
    public List<DepartamentDto> lexoDepartamentPunonjes() {
        return departamentRepository.lexoDepartamentPunonjes();
    }

    @Override
    public DepartamentDto kerkoDepartament(int id) {
        return departamentRepository.kerkoDepartament(id);
    }

    @Override
    public void fshiDepartament(int id) {
        departamentRepository.fshiDepartament(id);
    }

}
