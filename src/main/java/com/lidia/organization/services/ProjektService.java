package com.lidia.organization.services;

import com.lidia.organization.dto.ProjektDto;
import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.model.Projekt;
import com.lidia.organization.model.StatusProjekt;
import com.lidia.organization.model.Task;
import com.lidia.organization.repositories.DepartamentRepository;
import com.lidia.organization.repositories.ProjektRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class ProjektService {

    private final ProjektRepository projektRepository;

    private final DepartamentRepository departamentRepository;

    public ProjektService(ProjektRepository projektRepository, DepartamentRepository departamentRepository) {
        this.projektRepository = projektRepository;
        this.departamentRepository = departamentRepository;
    }

    public void shtoProjekt(ProjektDto projektDto){
        projektRepository.save(toProjekt().apply(projektDto));
    }

    public ProjektDto kerkoProjekt(int id){
        if(projektRepository.findById(id) != null)
            return toProjektDto().apply(projektRepository.findById(id));
        else
            return null;
    }

    public List<ProjektDto> lexoProjektet(){
        return projektRepository.findAll().stream().map(toProjektDto()).toList();
    }

    public void fshiProjekt(int id){
        projektRepository.deleteProjektById(id);
    }

    public void ndryshoProjekt(ProjektDto projektDto){
        projektRepository.save(toProjekt().apply(projektDto));
    }

    private static Function<Projekt, ProjektDto> toProjektDto() {
        return projekt -> {
            ProjektDto projektDto = new ProjektDto();
            projektDto.setId(projekt.getId());
            projektDto.setTitull(projekt.getTitull());
            projektDto.setDataNisje(projekt.getDataNisje());
            projektDto.setDataPerfundim(projekt.getDataPerfundim());
            projektDto.setStatus(String.valueOf(projekt.getStatus()));
            if(projekt.getDepartament() != null){
                projektDto.setDepartamentId(projekt.getDepartament().getId());
            }
            List<TaskDto> taskDtoList = projekt.getTaskList().stream().map(toTaskDto()).toList();
            projektDto.setTaskDtoList(taskDtoList);
            return projektDto;
        };
    }

    private Function<ProjektDto, Projekt> toProjekt() {
        return projektDto -> {
            Projekt projekt = new Projekt();
            projekt.setId(projektDto.getId());
            projekt.setTitull(projektDto.getTitull());
            projekt.setDataNisje(projektDto.getDataNisje());
            projekt.setDataPerfundim(projektDto.getDataPerfundim());
            projekt.setStatus(StatusProjekt.valueOf(projektDto.getStatus()));
            projekt.setDepartament(departamentRepository.findById(projektDto.getDepartamentId()));
            return projekt;
        };
    }

    private static Function<Task, TaskDto> toTaskDto() {
        return task -> {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setTitull(task.getTitull());
            taskDto.setStatus(String.valueOf(task.getStatus()));
            if(task.getPunonjes() != null){
                taskDto.setPunonjesId(task.getPunonjes().getId());
            }
            return taskDto;
        };
    }

}
