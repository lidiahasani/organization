package com.lidia.organization.services.api;

import com.lidia.organization.dto.DepartamentDto;

import java.util.List;

public interface DepartamentService {

    void krijoDepartament(DepartamentDto departamentDto);

    List<DepartamentDto> lexoDepartamentet();

    List<DepartamentDto> lexoDepartamentPunonjes();

    DepartamentDto kerkoDepartament(int id);

    void ndryshoDepartament(DepartamentDto departamentDto);

    void fshiDepartament(int id);

}
