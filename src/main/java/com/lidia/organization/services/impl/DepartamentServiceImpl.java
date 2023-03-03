package com.lidia.organization.services.impl;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.repositories.DepartamentJdbcRepository;
import com.lidia.organization.services.api.DepartamentService;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartamentServiceImpl implements DepartamentService {

    private final DepartamentJdbcRepository departamentRepository;

    public DepartamentServiceImpl(DepartamentJdbcRepository departamentRepository) {
        this.departamentRepository = departamentRepository;
    }

    @Override
    public void krijoDepartament(DepartamentDto departamentDto) {
        departamentRepository.krijoDepartament(departamentDto.getEmer());
    }

    @Override
    public List<DepartamentDto> lexoDepartamentet() {
        return departamentRepository.lexoDepartamentet(departamentDtoRowMapper());
    }

    @Override
    public List<DepartamentDto> lexoDepartamentPunonjes() {
        return departamentRepository.lexoDepartamentPunonjes(this::departamentDtoSetExtractor);
    }

    @Override
    public DepartamentDto kerkoDepartament(int id) {
        return departamentRepository.kerkoDepartament(id, departamentDtoRowMapper());
    }

    @Override
    public void fshiDepartament(int id) {
        departamentRepository.fshiDepartament(id);
    }

    public RowMapper<DepartamentDto> departamentDtoRowMapper() {
        return (r, i) -> {
            DepartamentDto rowObject = new DepartamentDto();
            rowObject.setId(r.getInt("id"));
            rowObject.setEmer(r.getString("emer"));
            return rowObject;
        };
    }

    public List<DepartamentDto> departamentDtoSetExtractor(ResultSet rs) throws SQLException {
        Map<Integer, DepartamentDto> departamentMap = new LinkedHashMap<>();

        while (rs.next()) {
            Integer departamentId = rs.getInt("departament_id");
            DepartamentDto departament = departamentMap.computeIfAbsent(departamentId, id -> {
                DepartamentDto d = new DepartamentDto();
                d.setId(id);
                try {
                    d.setEmer(rs.getString("departament_emer"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                d.setPunonjesDtos(new ArrayList<>());
                d.setProjektDtos(new ArrayList<>());
                return d;
            });

            if (rs.getInt("punonjes_id") != 0) {
                PunonjesDto punonjes = new PunonjesDto();
                punonjes.setId(rs.getInt("punonjes_id"));
                punonjes.setEmer(rs.getString("punonjes_emer"));
                punonjes.setEmail(rs.getString("punonjes_email"));
                departament.getPunonjesDtos().add(punonjes);
            }
        }

        return new ArrayList<>(departamentMap.values());
    }

}
