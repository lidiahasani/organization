package com.lidia.organization.services;

import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.repositories.ProjektRepository;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.repositories.TaskRepository;
import com.lidia.organization.util.Mapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final ProjektRepository projektRepository;

    private final PunonjesRepository punonjesRepository;

    private final Mapper mapper;

    public TaskService(TaskRepository taskRepository, ProjektRepository projektRepository, PunonjesRepository punonjesRepository, Mapper mapper) {
        this.taskRepository = taskRepository;
        this.projektRepository = projektRepository;
        this.punonjesRepository = punonjesRepository;
        this.mapper = mapper;
    }

    public void shtoTask(TaskDto taskDto) {
        taskRepository.save(mapper.toTask().apply(taskDto));
    }

    public TaskDto kerkoTask(int id){
        if(taskRepository.findById(id) != null)
            return mapper.toTaskDto().apply(taskRepository.findById(id));
        else
            return null;
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

    // TODO : DEFINE MAPPINGS OUTSIDE OF THE SERVICE CLASS


}
