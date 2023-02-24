package com.lidia.organization.services;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.model.Projekt;
import com.lidia.organization.model.Punonjes;
import com.lidia.organization.model.Task;
import com.lidia.organization.repositories.DepartamentRepository;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class PunonjesService {

    private final PunonjesRepository punonjesRepository;

    private final DepartamentRepository departamentRepository;
    private final TaskRepository taskRepository;

    public PunonjesService(PunonjesRepository punonjesRepository, DepartamentRepository departamentRepository, TaskRepository taskRepository) {
        this.punonjesRepository = punonjesRepository;
        this.departamentRepository = departamentRepository;
        this.taskRepository = taskRepository;
    }

    public void shtoPunonjes(PunonjesDto punonjesDto) {
        punonjesRepository.save(toPunonjes().apply(punonjesDto));
    }

    public PunonjesDto kerkoPunonjes(String emer){
        if(punonjesRepository.findByEmer(emer) != null)
            return toPunonjesDto().apply(punonjesRepository.findByEmer(emer));
        else
            return null;
    }

    public List<PunonjesDto> lexoPunonjes(){
        return punonjesRepository.findAll().stream().map(toPunonjesDto()).toList();
    }

    // TODO : Use dto or not?
    public void fshiPunonjes(int id){
        Punonjes punonjes = punonjesRepository.findById(id);

        for (Task task : punonjes.getTaskList()) {
            task.setPunonjes(null);
            taskRepository.save(task);
        }

        punonjesRepository.deletePunonjesById(id);
    }

    public void ndryshoPunonjes(PunonjesDto punonjesDto){
        punonjesRepository.save(toPunonjes().apply(punonjesDto));
    }

    private static Function<Punonjes, PunonjesDto> toPunonjesDto() {
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

    // TODO : FIX UPDATES
    private Function<PunonjesDto, Punonjes> toPunonjes() {
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

    private static Function<Task, TaskDto> toTaskDto() {
        return task -> {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setTitull(task.getTitull());
            taskDto.setStatus(String.valueOf(task.getStatus()));
            if(task.getProjekt() != null){
                taskDto.setProjektId(task.getProjekt().getId());
            }
            return taskDto;
        };
    }
}
