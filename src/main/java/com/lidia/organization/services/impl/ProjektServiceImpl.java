package com.lidia.organization.services.impl;

import com.lidia.organization.dto.ProjektDto;
import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.model.Projekt;
import com.lidia.organization.model.StatusProjekt;
import com.lidia.organization.repositories.DepartamentRepository;
import com.lidia.organization.repositories.ProjektRepository;
import com.lidia.organization.services.api.ProjektService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProjektServiceImpl implements ProjektService {

    private final ProjektRepository projektRepository;

    private final DepartamentRepository departamentRepository;

    private final TaskServiceImpl taskService;

    public ProjektServiceImpl(ProjektRepository projektRepository, DepartamentRepository departamentRepository, TaskServiceImpl taskService) {
        this.projektRepository = projektRepository;
        this.departamentRepository = departamentRepository;
        this.taskService = taskService;
    }

    @Override
    public void shtoProjekt(ProjektDto projektDto){
        projektRepository.save(toProjekt().apply(projektDto));
    }

    @Override
    public ProjektDto kerkoProjekt(int id){
        return projektRepository.findById(id).map(toProjektDto())
                .orElseThrow(() -> new EntityNotExistsException("Projekti nuk ekziston."));
    }

    @Override
    public List<ProjektDto> lexoProjektet(){
        return projektRepository.findAll().stream().map(toProjektDto()).toList();
    }

    @Override
    public void fshiProjekt(int id){
        projektRepository.deleteProjektById(id);
    }

    @Override
    public void ndryshoProjekt(ProjektDto projektDto){
        projektRepository.save(toProjekt().apply(projektDto));
    }

    @Override
    public List<ProjektDto> lexoProjektetmeKushtTitull(String titull){
        return projektRepository.findAllByTitullEndingWith(titull).stream().map(toProjektDto()).toList();
    }

    @Override
    public List<ProjektDto> lexoProjektetmeKushtDate(Date date){
        return projektRepository.findAllByDataNisjeGreaterThan(date).stream().map(toProjektDto()).toList();
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
            List<TaskDto> taskDtoList = projekt.getTaskList().stream().map(taskService.toTaskDto()).toList();
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

}
