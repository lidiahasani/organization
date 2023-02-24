package com.lidia.organization.controllers;

import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.services.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/task/shto")
    public void shtoTask(
            @RequestBody TaskDto taskDto){
        taskService.shtoTask(taskDto);
    }

    @GetMapping("/task/kerko/{id}")
    public TaskDto kerkoTask(
            @PathVariable("id") Integer id){
        return taskService.kerkoTask(id);
    }

    @GetMapping("/task/lexo")
    public List<TaskDto> lexoTask(){
        return taskService.lexoTasket();
    }

    @DeleteMapping("/task/fshi/{id}")
    public void fshiTask(
            @PathVariable("id") Integer id){
        taskService.fshiTask(id);
    }

    @PostMapping("/task/ndrysho")
    public void ndryshoTask(
            @RequestBody TaskDto taskDto){
        taskService.ndryshoTask(taskDto);
    }
}
