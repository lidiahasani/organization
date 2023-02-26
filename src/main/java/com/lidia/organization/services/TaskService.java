package com.lidia.organization.services;

import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.repositories.TaskRepository;
import com.lidia.organization.util.Mapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final Mapper mapper;

    public TaskService(TaskRepository taskRepository, Mapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public void shtoTask(TaskDto taskDto) {
        taskRepository.save(mapper.toTask().apply(taskDto));
    }

    public TaskDto kerkoTask(int id){
        return taskRepository.findById(id).map(mapper.toTaskDto())
                .orElseThrow(() -> new EntityNotExistsException("Tasku i kerkuar nuk ekziston."));
    }

    public List<TaskDto> lexoTasket(){
        return taskRepository.findAll().stream().map(mapper.toTaskDto()).toList();
    }

    public void fshiTask(int id){
        taskRepository.deleteTaskById(id);
    }

    public void ndryshoTask(TaskDto taskDto){
        taskRepository.save(mapper.toTask().apply(taskDto));
    }

}
