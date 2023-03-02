package com.lidia.organization.util;

import com.lidia.organization.dto.*;
import com.lidia.organization.exception.EmailNotUniqueException;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.model.*;
import com.lidia.organization.repositories.DepartamentRepository;
import com.lidia.organization.repositories.ProjektRepository;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.repositories.RoleRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

@Component
public class Mapper {

    private final DepartamentRepository departamentRepository;

    private final ProjektRepository projektRepository;

    private final PunonjesRepository punonjesRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public Mapper(DepartamentRepository departamentRepository, ProjektRepository projektRepository, PunonjesRepository punonjesRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.departamentRepository = departamentRepository;
        this.projektRepository = projektRepository;
        this.punonjesRepository = punonjesRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public Function<Projekt, ProjektDto> toProjektDto() {
        return projekt -> {
            ProjektDto projektDto = new ProjektDto();
            projektDto.setId(projekt.getId());
            projektDto.setTitull(projekt.getTitull());
            projektDto.setDataNisje(projekt.getDataNisje());
            projektDto.setDataPerfundim(projekt.getDataPerfundim());
            projektDto.setStatus(String.valueOf(projekt.getStatus()));
            Optional.ofNullable(projekt.getDepartament())
                    .ifPresent(departament ->projektDto.setDepartamentId(projekt.getDepartament().getId()));
            List<TaskDto> taskDtoList = projekt.getTaskList().stream().map(toTaskDto()).toList();
            projektDto.setTaskDtoList(taskDtoList);
            return projektDto;
        };
    }

    public Function<ProjektDto, Projekt> toProjekt() {
        return projektDto -> {
            Projekt projekt = new Projekt();
            projekt.setId(projektDto.getId());
            projekt.setTitull(projektDto.getTitull());
            projekt.setDataNisje(projektDto.getDataNisje());
            projekt.setDataPerfundim(projektDto.getDataPerfundim());
            projekt.setStatus(StatusProjekt.valueOf(projektDto.getStatus()));
            if(projektDto.getDepartamentId() != 0) {
                departamentRepository.findById(projektDto.getDepartamentId()).
                        ifPresentOrElse(projekt::setDepartament, () -> {
                            throw new EntityNotExistsException("Departamenti nuk ekziston.");
                        });
            }
            return projekt;
        };
    }

    public Function<Task, TaskDto> toTaskDto() {
        return task -> {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setTitull(task.getTitull());
            taskDto.setStatus(String.valueOf(task.getStatus()));
            Optional.ofNullable(task.getProjekt())
                    .ifPresent(projekt -> taskDto.setProjektId(task.getProjekt().getId()));
            Optional.ofNullable(task.getPunonjes())
                    .ifPresent(punonjes -> taskDto.setPunonjesId(task.getPunonjes().getId()));
            return taskDto;
        };
    }

    public Function<TaskDto, Task> toTask() {
        return taskDto -> {
            Task task = new Task();
            task.setId(taskDto.getId());
            task.setStatus(StatusTask.valueOf(taskDto.getStatus()));
            if(taskDto.getProjektId() != 0) {
                projektRepository.findById(taskDto.getProjektId()).
                        ifPresentOrElse(task::setProjekt, () -> {
                                    throw new EntityNotExistsException("Projekti nuk ekziston.");
                        });
            }
            if(taskDto.getPunonjesId() != 0) {
                punonjesRepository.findById(taskDto.getPunonjesId()).
                        ifPresentOrElse(task::setPunonjes, () -> {
                                    throw new EntityNotExistsException("Punonjesi nuk ekziston.");
                        });
            }
            return task;
        };
    }

    public Function<Punonjes, PunonjesDto> toPunonjesDto() {
        return punonjes -> {
            PunonjesDto punonjesDto = new PunonjesDto();
            punonjesDto.setId(punonjes.getId());
            punonjesDto.setEmer(punonjes.getEmer());
            punonjesDto.setEmail(punonjes.getEmail());
            Optional.ofNullable(punonjes.getDepartament())
                    .ifPresent(departament -> punonjesDto.setDepartamentId(punonjes.getDepartament().getId()));
            List<TaskDto> taskDtoList = punonjes.getTaskList().stream().map(toTaskDto()).toList();
            punonjesDto.setTaskDtoList(taskDtoList);
            List<RoleDto> roleDtoList = punonjes.getRole().stream().map(rol -> new RoleDto(String.valueOf(rol.getEmer()))).toList();
            punonjesDto.setRoleDtoList(roleDtoList);
            return punonjesDto;
        };
    }

    public Function<PunonjesDto, Punonjes> toPunonjes() {
        return signupRequest -> {
            Punonjes punonjes = new Punonjes();
            if (punonjesRepository.existsByEmail(signupRequest.getEmail())) {
                throw new EmailNotUniqueException("Punonjesi me kete email ekziston.");
            }
            punonjes.setEmer(signupRequest.getEmer());
            punonjes.setEmail(signupRequest.getEmail());
            punonjes.setPassword(encoder.encode(signupRequest.getPassword()));

            if(signupRequest.getDepartamentId() != 0) {
                departamentRepository.findById(signupRequest.getDepartamentId()).
                        ifPresentOrElse(punonjes::setDepartament, () -> {
                            throw new EntityNotExistsException("Departamenti nuk ekziston.");
                        });
            }

            List<ERole> roleNames = signupRequest.getRoleDtoList()
                    .stream()
                    .map(RoleDto::emer)
                    .map(ERole::valueOf)
                    .toList();

            List<Role> roles = roleRepository.findAllByEmerIn(roleNames);

            punonjes.setRole(roles);
            return punonjes;
        };
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
