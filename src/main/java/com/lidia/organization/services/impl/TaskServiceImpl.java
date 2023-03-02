package com.lidia.organization.services.impl;

import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.repositories.TaskRepository;
import com.lidia.organization.services.MapperService;
import com.lidia.organization.services.api.TaskService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final MapperService mapper;

    public TaskServiceImpl(TaskRepository taskRepository, MapperService mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    public void shtoTask(TaskDto taskDto) {
        taskRepository.save(mapper.toTask().apply(taskDto));
    }

    @Override
    public TaskDto kerkoTask(int id){
        return taskRepository.findById(id).map(mapper.toTaskDto())
                .orElseThrow(() -> new EntityNotExistsException("Tasku i kerkuar nuk ekziston."));
    }

    @Override
    public List<TaskDto> lexoTasket(){
        return taskRepository.findAll().stream().map(mapper.toTaskDto()).toList();
    }

    @Override
    public void fshiTask(int id){
        taskRepository.deleteTaskById(id);
    }

    @Override
    public void ndryshoTask(TaskDto taskDto){
        taskRepository.save(mapper.toTask().apply(taskDto));
    }

}
