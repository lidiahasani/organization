package com.lidia.organization.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDto {

    private int id;

    private String name;

    private List<EmployeeDto> employeeDtos = new ArrayList<>();

    private List<ProjectDto> projectDtos = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDto> getEmployeeDtos() {
        return employeeDtos;
    }

    @JsonIgnoreProperties({"departamentId"})
    public void setEmployeeDtos(List<EmployeeDto> employeeDtos) {
        this.employeeDtos = employeeDtos;
    }

    public List<ProjectDto> getProejctDtos() {
        return projectDtos;
    }

    @JsonIgnoreProperties({"departamentId"})
    public void setProjectDtos(List<ProjectDto> projectDtos) {
        this.projectDtos = projectDtos;
    }
}


