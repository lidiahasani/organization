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

    @PostMapping("/shto")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto shtoTask(
            @RequestBody TaskDto taskDto){
        return taskService.shtoOseNdryshoTask(taskDto);
    }

    @GetMapping("/kerko/{id}")
    public TaskDto kerkoTask(
            @PathVariable("id") Integer id){
        return taskService.kerkoTask(id);
    }

    @GetMapping("/lexo")
    public List<TaskDto> lexoTask(){
        return taskService.lexoTasket();
    }

    @DeleteMapping("/fshi/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fshiTask(
            @PathVariable("id") Integer id){
        taskService.fshiTask(id);
    }

    @PostMapping("/ndrysho")
    public TaskDto ndryshoTask(
            @RequestBody TaskDto taskDto){
        return taskService.shtoOseNdryshoTask(taskDto);
    }
}
