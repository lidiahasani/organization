package com.lidia.organization.services.api;

import com.lidia.organization.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto createOrUpdate(DepartmentDto departmentDto);

    List<DepartmentDto> read();

    DepartmentDto read(int id);

    void delete(int id);

}
