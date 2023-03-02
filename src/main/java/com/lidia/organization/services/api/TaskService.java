package com.lidia.organization.services.api;

import com.lidia.organization.dto.TaskDto;

import java.util.List;

public interface TaskService {

    void shtoTask(TaskDto taskDto);

    TaskDto kerkoTask(int id);

    List<TaskDto> lexoTasket();

    void fshiTask(int id);

    void ndryshoTask(TaskDto taskDto);

}
