package com.lidia.organization.repositories;

import com.lidia.organization.dto.DepartamentDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartamentRepositoryJdbc {
        private final JdbcTemplate jdbc;

        public DepartamentRepositoryJdbc(JdbcTemplate jdbc) {
            this.jdbc = jdbc;
        }

        // TODO : SERVICE CLASS
        public void krijoDepartament(String emer) {
            String sql =
                    "INSERT INTO departament (emer) VALUES (?)";

            jdbc.update(sql, emer);
        }

        public List<DepartamentDto> lexoDepartamentet() {
            String sql = "SELECT * FROM departament";
            return jdbc.query(sql, departamentDtoRowMapper());
        }

        private static RowMapper<DepartamentDto> departamentDtoRowMapper() {
            return (r, i) -> {
                DepartamentDto rowObject = new DepartamentDto();
                rowObject.setId(r.getInt("id"));
                rowObject.setEmer(r.getString("emer"));
                // TODO : set Projekt and Punonjes for JOIN queries
                return rowObject;
            };
        }

        public Optional<DepartamentDto> kerkoDepartament(int id) {
            String sql = "SELECT * FROM departament WHERE departament.id = ?";

            DepartamentDto departamentDto = null;
            try{
                departamentDto = jdbc.queryForObject(sql, departamentDtoRowMapper(), new Object[]{id});
            }
            catch(DataAccessException exception){
            }
            return Optional.ofNullable(departamentDto);
        }

        public void ndryshoEmer(DepartamentDto departamentDto){

            String sql = "UPDATE departament SET emer = ? WHERE departament.id = ?";

            jdbc.update(sql, departamentDto.getEmer(), departamentDto.getId());
        }

        public void fshiDepartament(int id){

            // TODO : Update departamentId = 0 per Projekt dhe Punonjes para se ta fshi

            String sql = "DELETE FROM departament WHERE id = ?";

            jdbc.update(sql, id);
        }
}
