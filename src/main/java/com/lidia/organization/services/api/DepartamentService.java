package com.lidia.organization.services.api;

import com.lidia.organization.dto.DepartamentDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface DepartamentService {

    void krijoDepartament(DepartamentDto departamentDto);

    List<DepartamentDto> lexoDepartamentet();

    List<DepartamentDto> lexoDepartamentPunonjes();

    DepartamentDto kerkoDepartament(int id);

    @Transactional
    void fshiDepartament(int id);
}
