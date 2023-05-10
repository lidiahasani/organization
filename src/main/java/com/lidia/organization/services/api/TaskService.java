package com.lidia.organization.services.api;

import com.lidia.organization.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto createOrUpdate(TaskDto taskDto);

    TaskDto read(int id);

    List<TaskDto> read();

    void delete(int id);

}
