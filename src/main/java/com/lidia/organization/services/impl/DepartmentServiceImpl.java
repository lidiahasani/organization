package com.lidia.organization.services.impl;

import com.lidia.organization.dto.DepartmentDto;
import com.lidia.organization.dto.EmployeeDto;
import com.lidia.organization.repositories.DepartmentJdbcRepository;
import com.lidia.organization.services.api.DepartmentService;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentJdbcRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentJdbcRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void create(DepartmentDto departmentDto) {
        departmentRepository.create(departmentDto.getName());
    }

    @Override
    public List<DepartmentDto> read() {
        return departmentRepository.read(this::departmentDtoSetExtractor);
    }

    @Override
    public DepartmentDto read(int id) {
        return departmentRepository.read(id, departmentDtoRowMapper());
    }

    @Override
    public void update(DepartmentDto departmentDto) {
        departmentRepository.update(departmentDto.getName(), departmentDto.getId());
    }

    @Override
    public void delete(int id) {
        departmentRepository.delete(id);
    }

    public RowMapper<DepartmentDto> departmentDtoRowMapper() {
        return (r, i) -> {
            DepartmentDto rowObject = new DepartmentDto();
            rowObject.setId(r.getInt("id"));
            rowObject.setName(r.getString("name"));
            return rowObject;
        };
    }

    public List<DepartmentDto> departmentDtoSetExtractor(ResultSet rs) throws SQLException {
        Map<Integer, DepartmentDto> departamentMap = new LinkedHashMap<>();

        while (rs.next()) {
            Integer departmentId = rs.getInt("department_id");
            DepartmentDto department = departamentMap.computeIfAbsent(departmentId, id -> {
                DepartmentDto d = new DepartmentDto();
                d.setId(id);
                try {
                    d.setName(rs.getString("department_name"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                d.setEmployeeDtos(new ArrayList<>());
                d.setProjectDtos(new ArrayList<>());
                return d;
            });

            if (rs.getInt("employee_id") != 0) {
                EmployeeDto employee = new EmployeeDto();
                employee.setId(rs.getInt("employee_id"));
                employee.setName(rs.getString("employee_name"));
                employee.setEmail(rs.getString("employee_email"));
                department.getEmployeeDtos().add(employee);
            }
        }

        return new ArrayList<>(departamentMap.values());
    }

}
