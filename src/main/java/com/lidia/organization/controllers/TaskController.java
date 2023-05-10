package com.lidia.organization.controllers;

import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.services.api.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(
            @RequestBody TaskDto taskDto){
        return taskService.createOrUpdate(taskDto);
    }

    @GetMapping("/read/{id}")
    public TaskDto read(
            @PathVariable("id") Integer id){
        return taskService.read(id);
    }

    @GetMapping("/read")
    public List<TaskDto> read(){
        return taskService.read();
    }

    @PostMapping("/update")
    public TaskDto update(
            @RequestBody TaskDto taskDto){
        return taskService.createOrUpdate(taskDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("id") Integer id){
        taskService.delete(id);
    }

}
