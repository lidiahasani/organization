package com.lidia.organization.repositories;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.exception.EntityNotExistsException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartamentJdbcRepository {

    private final JdbcTemplate jdbc;

    public DepartamentJdbcRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void krijoDepartament(String emer) {
        String sql = """
                INSERT INTO departament (emer)
                VALUES (?);
                """;
        jdbc.update(sql, emer);
    }

    public List<DepartamentDto> lexoDepartamentet(RowMapper<DepartamentDto> rowMapper) {
        String sql = """
                SELECT *
                FROM departament;
        """;
        return jdbc.query(sql, rowMapper);
    }

    public List<DepartamentDto> lexoDepartamentPunonjes(ResultSetExtractor<List<DepartamentDto>> resultSetExtractor) {
        String sql = """
                SELECT d.id as departament_id, d.emer as departament_emer, p.id as punonjes_id, p.emer as punonjes_emer, p.email as punonjes_email
                FROM departament d
                LEFT JOIN punonjes p ON d.id = p.id_departament;
                """;
        return jdbc.query(
                sql, resultSetExtractor);
    }

    public DepartamentDto kerkoDepartament(int id, RowMapper<DepartamentDto> rowMapper) {
        String sql = """
                SELECT *
                FROM departament
                WHERE departament.id = ?;
                """;
        try{
            return jdbc.queryForObject(sql, rowMapper, id);
        }
        catch (DataAccessException e) {
                throw new EntityNotExistsException("Departamenti nuk ekziston.");
        }
    }

    @Transactional
    public void fshiDepartament(int id){
        String sqlProjekt = """
                UPDATE projekt
                SET id_departament = null
                WHERE projekt.id_departament = ?;
        """;
        jdbc.update(sqlProjekt, id);

        String sqlPunonjes = """
                UPDATE punonjes
                SET id_departament = null
                WHERE punonjes.id_departament = ?;
                """;
        jdbc.update(sqlPunonjes, id);

        String sql = """
                DELETE
                FROM departament
                WHERE id = ?;
                """;
        jdbc.update(sql, id);
    }
}
