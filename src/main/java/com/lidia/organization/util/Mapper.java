package com.lidia.organization.util;

import com.lidia.organization.dto.ProjektDto;
import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.model.*;
import com.lidia.organization.repositories.DepartamentRepository;
import com.lidia.organization.repositories.ProjektRepository;
import com.lidia.organization.repositories.PunonjesRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class Mapper {

    private final DepartamentRepository departamentRepository;

    private final ProjektRepository projektRepository;

    private final PunonjesRepository punonjesRepository;

    public Mapper(DepartamentRepository departamentRepository, ProjektRepository projektRepository, PunonjesRepository punonjesRepository) {
        this.departamentRepository = departamentRepository;
        this.projektRepository = projektRepository;
        this.punonjesRepository = punonjesRepository;
    }

    public Function<Projekt, ProjektDto> toProjektDto() {
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

    public Function<ProjektDto, Projekt> toProjekt() {
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

    public Function<Task, TaskDto> toTaskDto() {
        return task -> {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setTitull(task.getTitull());
            taskDto.setStatus(String.valueOf(task.getStatus()));
            if(task.getProjekt() != null){
                taskDto.setProjektId(task.getProjekt().getId());
            }
            if(task.getPunonjes() != null){
                taskDto.setPunonjesId(task.getPunonjes().getId());
            }
            return taskDto;
        };
    }

    public Function<TaskDto, Task> toTask() {
        return taskDto -> {
            Task task = new Task();
            task.setId(taskDto.getId());
            task.setTitull(taskDto.getTitull());
            task.setStatus(StatusTask.valueOf(taskDto.getStatus()));
            if(taskDto.getProjektId() != 0){
                task.setProjekt(projektRepository.findById(taskDto.getProjektId()));
            }
            else
                task.setProjekt(null);
            if(taskDto.getPunonjesId() != 0){
                task.setPunonjes(punonjesRepository.findById(taskDto.getPunonjesId()));
            }
            else
                task.setProjekt(null);
            return task;
        };
    }

    public Function<Punonjes, PunonjesDto> toPunonjesDto() {
        return punonjes -> {
            PunonjesDto punonjesDto = new PunonjesDto();
            punonjesDto.setId(punonjes.getId());
            punonjesDto.setEmer(punonjes.getEmer());
            punonjesDto.setEmail(punonjes.getEmail());
            if(punonjes.getDepartament() != null){
                punonjesDto.setDepartamentId(punonjes.getDepartament().getId());
            }
            List<TaskDto> taskDtoList = punonjes.getTaskList().stream().map(toTaskDto()).toList();
            punonjesDto.setTaskDtoList(taskDtoList);
            return punonjesDto;
        };
    }

    public Function<PunonjesDto, Punonjes> toPunonjes() {
        return punonjesDto -> {
            Punonjes punonjes = new Punonjes();
            punonjes.setId(punonjesDto.getId());
            punonjes.setEmer(punonjesDto.getEmer());
            punonjes.setEmail(punonjesDto.getEmail());
            if(punonjesDto.getDepartamentId() != 0){
                punonjes.setDepartament(departamentRepository.findById(punonjesDto.getDepartamentId()));
            }
            else
               punonjes.setDepartament(null);
            return punonjes;
        };
    }

}
