package com.lidia.organization.services.impl;

import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.model.StatusTask;
import com.lidia.organization.model.Task;
import com.lidia.organization.repositories.ProjektRepository;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.repositories.TaskRepository;
import com.lidia.organization.services.api.TaskService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final ProjektRepository projektRepository;

    private final PunonjesRepository punonjesRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjektRepository projektRepository, PunonjesRepository punonjesRepository) {
        this.taskRepository = taskRepository;
        this.projektRepository = projektRepository;
        this.punonjesRepository = punonjesRepository;
    }

    @Override
    public void shtoTask(TaskDto taskDto) {
        taskRepository.save(toTask().apply(taskDto));
    }

    @Override
    public TaskDto kerkoTask(int id){
        return taskRepository.findById(id).map(toTaskDto())
                .orElseThrow(() -> new EntityNotExistsException("Tasku i kerkuar nuk ekziston."));
    }

    @Override
    public List<TaskDto> lexoTasket(){
        return taskRepository.findAll().stream().map(toTaskDto()).toList();
    }

    @Override
    public void fshiTask(int id){
        taskRepository.deleteTaskById(id);
    }

    @Override
    public void ndryshoTask(TaskDto taskDto){
        taskRepository.save(toTask().apply(taskDto));
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
            task.setTitull(taskDto.getTitull());
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
}
