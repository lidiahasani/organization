package com.lidia.organization.services;

import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.model.*;
import com.lidia.organization.repositories.ProjektRepository;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Function;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final ProjektRepository projektRepository;

    private final PunonjesRepository punonjesRepository;

    public TaskService(TaskRepository taskRepository, ProjektRepository projektRepository, PunonjesRepository punonjesRepository) {
        this.taskRepository = taskRepository;
        this.projektRepository = projektRepository;
        this.punonjesRepository = punonjesRepository;
    }

    public void shtoTask(TaskDto taskDto) {

        taskRepository.save(toTask().apply(taskDto));
    }

    public TaskDto kerkoTask(int id){
        if(taskRepository.findById(id) != null)
            return toTaskDto().apply(taskRepository.findById(id));
        else
            return null;
    }

    public List<TaskDto> lexoTasket(){
        return taskRepository.findAll().stream().map(toTaskDto()).toList();
    }

    public void fshiTask(int id){
        taskRepository.deleteTaskById(id);
    }

    public void ndryshoTask(TaskDto taskDto){
        taskRepository.save(toTask().apply(taskDto));
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
            if(task.getPunonjes() != null){
                taskDto.setPunonjesId(task.getPunonjes().getId());
            }
            return taskDto;
        };
    }

    private Function<TaskDto, Task> toTask() {
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

}
