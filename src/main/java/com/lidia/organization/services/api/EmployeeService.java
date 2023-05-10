package com.lidia.organization.services.api;

import com.lidia.organization.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    void create(EmployeeDto employeeDto);

    EmployeeDto read(String name);

    List<EmployeeDto> read();

    EmployeeDto update(EmployeeDto employeeDto);

    void delete(int id);

}
