package com.lidia.organization.services.api;

import com.lidia.organization.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto shtoOseNdryshoTask(TaskDto taskDto);

    TaskDto kerkoTask(int id);

    List<TaskDto> lexoTasket();

    void fshiTask(int id);

}
