package com.lidia.organization.repositories;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.util.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartamentRepositoryJdbc {
        private final JdbcTemplate jdbc;

        private final Mapper mapper;
        public DepartamentRepositoryJdbc(JdbcTemplate jdbc, Mapper mapper) {
            this.jdbc = jdbc;
            this.mapper = mapper;
        }

        public void krijoDepartament(String emer) {
            String sql =
                    "INSERT INTO departament (emer) VALUES (?)";

            jdbc.update(sql, emer);
        }

        public List<DepartamentDto> lexoDepartamentet() {
            String sql = "SELECT * FROM departament";
            return jdbc.query(sql, mapper.departamentDtoRowMapper());
        }

        public DepartamentDto kerkoDepartament(int id) {
            String sql = "SELECT * FROM departament WHERE departament.id = ?";

            try{
                return jdbc.queryForObject(sql, mapper.departamentDtoRowMapper(), new Object[]{id});
            }
            catch (DataAccessException e) {
                    throw new EntityNotExistsException("Departamenti nuk ekziston.");
            }
        }

        public void ndryshoEmer(String emer, int id){
            String sql = "UPDATE departament SET emer = ? WHERE departament.id = ?";

            jdbc.update(sql, emer, id);
        }

        public void fshiDepartament(int id){

            String sql = "UPDATE projekt SET id_departament = null WHERE projekt.id_departament = ?";

            jdbc.update(sql, id);

            sql = "UPDATE punonjes SET id_departament = null WHERE punonjes.id_departament = ?";

            jdbc.update(sql, id);

            sql = "DELETE FROM departament WHERE id = ?";

            jdbc.update(sql, id);
        }
}
