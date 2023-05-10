package com.lidia.organization.repositories;

import com.lidia.organization.dto.DepartmentDto;
import com.lidia.organization.exception.EntityNotExistsException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentJdbcRepository {

    private final JdbcTemplate jdbc;

    public DepartmentJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void create(String emer) {
        String sql = """
                INSERT INTO department (name)
                VALUES (?);
                """;
        jdbc.update(sql, emer);
    }

    public List<DepartmentDto> read(ResultSetExtractor<List<DepartmentDto>> resultSetExtractor) {
        String sql = """
                SELECT d.id as department_id, d.name as department_name, p.id as employee_id, p.name as employee_name, p.email as employee_email
                FROM department d
                LEFT JOIN employee p ON d.id = p.id_department;
                """;
        return jdbc.query(
                sql, resultSetExtractor);
    }

    public DepartmentDto read(int id, RowMapper<DepartmentDto> rowMapper) {
        String sql = """
                SELECT *
                FROM department
                WHERE department.id = ?;
                """;
        try {
            return jdbc.queryForObject(sql, rowMapper, id);
        } catch (DataAccessException e) {
            throw new EntityNotExistsException("Department with the required id does not exist.");
        }
    }

    public void update(String emer, int id) {
        String sql = """
                        UPDATE department
                        SET name = ?
                        WHERE id = ?;
                """;
        int rowsAffected = jdbc.update(sql, emer, id);

        if (rowsAffected == 0)
            throw new IllegalArgumentException("Unsuccessful update!");
    }

    @Transactional
    public void delete(int id) {
        String sqlProject = """
                        UPDATE project
                        SET id_department = null
                        WHERE project.id_department = ?;
                """;
        jdbc.update(sqlProject, id);

        String sqlEmployee = """
                UPDATE employee
                SET id_department = null
                WHERE employee.id_department = ?;
                """;
        jdbc.update(sqlEmployee, id);

        String sql = """
                DELETE
                FROM department
                WHERE id = ?;
                """;
        jdbc.update(sql, id);
    }

}
