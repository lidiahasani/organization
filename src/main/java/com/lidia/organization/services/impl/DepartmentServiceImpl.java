package com.lidia.organization.services.impl;

import com.lidia.organization.dto.DepartmentDto;
import com.lidia.organization.dto.EmployeeDto;
import com.lidia.organization.dto.ProjectDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.model.Department;
import com.lidia.organization.repositories.DepartmentRepository;
import com.lidia.organization.repositories.EmployeeRepository;
import com.lidia.organization.repositories.ProjectRepository;
import com.lidia.organization.services.api.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    private final ProjectRepository projectRepository;

    private final EmployeeServiceImpl employeeService;

    private final ProjectServiceImpl projectService;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ProjectRepository projectRepository, EmployeeServiceImpl employeeService, ProjectServiceImpl projectService) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @Override
    public DepartmentDto createOrUpdate(DepartmentDto departmentDto) {
        var department = departmentRepository.save(toDepartment().apply(departmentDto));
        return toDepartmentDto().apply(department);
    }

    @Override
    public List<DepartmentDto> read() {
        return departmentRepository.findAll().stream().map(toDepartmentDto()).toList();    }

    @Override
    public DepartmentDto read(int id) {
        return departmentRepository.findById(id).map(toDepartmentDto())
                .orElseThrow(() -> new EntityNotExistsException("Department does not exist."));    }

    @Override
    public void delete(int id) {
        employeeRepository.findByDepartmentId(id).forEach(employee -> {
            employee.setDepartment(null);
            employeeRepository.save(employee);
        });
        projectRepository.findByDepartmentId(id).forEach(project -> {
            project.setDepartment(null);
            projectRepository.save(project);
        });
        departmentRepository.deleteDepartmentById(id);
    }

    public Function<Department, DepartmentDto> toDepartmentDto() {
        return department -> {
            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setId(department.getId());
            departmentDto.setName(department.getName());
            List<EmployeeDto> employeeDtoList = department.getEmployees().stream().map(employeeService.toEmployeeDto()).toList();
            departmentDto.setEmployeeDtos(employeeDtoList);
            List<ProjectDto> projectDtoList = department.getProjectList().stream().map(projectService.toProjectDto()).toList();
            departmentDto.setProjectDtos(projectDtoList);
            return departmentDto;
        };
    }

    public Function<DepartmentDto, Department> toDepartment() {
        return departmentDto -> {
            Department department = new Department();
            department.setName(departmentDto.getName());
            return department;
        };
    }

}
