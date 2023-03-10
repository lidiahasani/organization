package com.lidia.organization.services.impl;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.dto.RoleDto;
import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.exception.EmailNotUniqueException;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.model.ERole;
import com.lidia.organization.model.Punonjes;
import com.lidia.organization.model.Role;
import com.lidia.organization.repositories.*;
import com.lidia.organization.services.api.PunonjesService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PunonjesServiceImpl implements PunonjesService {

    private final PunonjesRepository punonjesRepository;

    private final TaskRepository taskRepository;

    private final TaskServiceImpl taskService;

    private final DepartamentRepository departamentRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public PunonjesServiceImpl(PunonjesRepository punonjesRepository, TaskRepository taskRepository, TaskServiceImpl taskService, DepartamentRepository departamentRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.punonjesRepository = punonjesRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.departamentRepository = departamentRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void regjistroPunonjes(PunonjesDto punonjesDto) {
        punonjesRepository.save(toPunonjes().apply(punonjesDto));
    }

    @Override
    public PunonjesDto kerkoPunonjes(String emer){
        return punonjesRepository.findByEmer(emer).map(toPunonjesDto())
                .orElseThrow(() -> new EntityNotExistsException("Punonjesi nuk ekziston."));
    }

    @Override
    public List<PunonjesDto> lexoPunonjes(){
        return punonjesRepository.findAll().stream().map(toPunonjesDto()).toList();
    }

    @Override
    public void fshiPunonjes(int id){
        taskRepository.findByPunonjesId(id).forEach(task -> {
            task.setPunonjes(null);
            taskRepository.save(task);
        });
        punonjesRepository.deletePunonjesById(id);
    }

    @Override
    public PunonjesDto ndryshoPunonjes(PunonjesDto punonjesDto){
        var punonjes = punonjesRepository.save(toPunonjesUpdate().apply(punonjesDto));
        return toPunonjesDto().apply(punonjes);
    }

    public Function<Punonjes, PunonjesDto> toPunonjesDto() {
        return punonjes -> {
            PunonjesDto punonjesDto = new PunonjesDto();
            punonjesDto.setId(punonjes.getId());
            punonjesDto.setEmer(punonjes.getEmer());
            punonjesDto.setEmail(punonjes.getEmail());
            Optional.ofNullable(punonjes.getDepartament())
                    .ifPresent(departament -> punonjesDto.setDepartamentId(punonjes.getDepartament().getId()));
            List<TaskDto> taskDtoList = punonjes.getTaskList().stream().map(taskService.toTaskDto()).toList();
            punonjesDto.setTaskDtoList(taskDtoList);
            List<RoleDto> roleDtoList = punonjes.getRole().stream().map(rol -> new RoleDto(String.valueOf(rol.getEmer()))).toList();
            punonjesDto.setRoleDtoList(roleDtoList);
            return punonjesDto;
        };
    }

    public Function<PunonjesDto, Punonjes> toPunonjes() {
        return punonjesDto -> {
            Punonjes punonjes = new Punonjes();
            if (punonjesRepository.existsByEmail(punonjesDto.getEmail())) {
                throw new EmailNotUniqueException();
            }

            punonjes.setEmer(punonjesDto.getEmer());
            punonjes.setEmail(punonjesDto.getEmail());
            punonjes.setPassword(encoder.encode(punonjesDto.getPassword()));

            if(punonjesDto.getDepartamentId() != 0) {
                departamentRepository.findById(punonjesDto.getDepartamentId()).
                        ifPresentOrElse(punonjes::setDepartament, () -> {
                            throw new EntityNotExistsException("Departamenti nuk ekziston.");
                        });
            }

            List<ERole> roleNames = punonjesDto.getRoleDtoList()
                    .stream()
                    .map(RoleDto::emer)
                    .map(ERole::valueOf)
                    .toList();

            List<Role> roles = roleRepository.findAllByEmerIn(roleNames);

            punonjes.setRole(roles);
            return punonjes;
        };
    }

    public Function<PunonjesDto, Punonjes> toPunonjesUpdate() {
        return punonjesDto -> punonjesRepository.findById(punonjesDto.getId())
                .map(
                        punonjes -> {
                            punonjes.setEmer(punonjesDto.getEmer());
                            if (punonjesRepository.existsByEmailAndIdIsNot(punonjesDto.getEmail(), punonjesDto.getId())) {
                                throw new EmailNotUniqueException();
                            } else {
                                punonjes.setEmail(punonjesDto.getEmail());
                            }
                            if (punonjesDto.getDepartamentId() != 0) {
                                departamentRepository.findById(punonjesDto.getDepartamentId()).
                                        ifPresentOrElse(punonjes::setDepartament, () -> {
                                            throw new EntityNotExistsException("Departamenti nuk ekziston.");
                                        });
                            }
                            List<ERole> roleNames = punonjesDto.getRoleDtoList()
                                    .stream()
                                    .map(RoleDto::emer)
                                    .map(ERole::valueOf)
                                    .toList();

                            List<Role> roles = roleRepository.findAllByEmerIn(roleNames);

                            punonjes.setRole(roles);
                            return punonjes;
                        }
                ).orElseThrow(() -> new EntityNotExistsException("Punonjesi me id-ne e kerkuar nuk ekziston"));
    }

}
