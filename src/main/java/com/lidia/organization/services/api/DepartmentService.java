package com.lidia.organization.services.api;

import com.lidia.organization.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    void create(DepartmentDto departmentDto);

    List<DepartmentDto> read();

    DepartmentDto read(int id);

    void update(DepartmentDto departmentDto);

    void delete(int id);

}
