package com.lidia.organization.controllers;

import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.services.api.TaskService;
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
    public void shtoTask(
            @RequestBody TaskDto taskDto){
        taskService.shtoTask(taskDto);
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
    public void fshiTask(
            @PathVariable("id") Integer id){
        taskService.fshiTask(id);
    }

    @PostMapping("/ndrysho")
    public void ndryshoTask(
            @RequestBody TaskDto taskDto){
        taskService.ndryshoTask(taskDto);
    }
}
