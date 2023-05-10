package com.lidia.organization.services.impl;

import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.model.TaskStatus;
import com.lidia.organization.model.Task;
import com.lidia.organization.repositories.ProjectRepository;
import com.lidia.organization.repositories.EmployeeRepository;
import com.lidia.organization.repositories.TaskRepository;
import com.lidia.organization.services.api.TaskService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    private final EmployeeRepository employeeRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public TaskDto createOrUpdate(TaskDto taskDto) {
        var task = taskRepository.save(toTask().apply(taskDto));
        return toTaskDto().apply(task);
    }

    @Override
    public TaskDto read(int id){
        return taskRepository.findById(id).map(toTaskDto())
                .orElseThrow(() -> new EntityNotExistsException("Task does not exist."));
    }

    @Override
    public List<TaskDto> read(){
        return taskRepository.findAll().stream().map(toTaskDto()).toList();
    }

    @Override
    public void delete(int id){
        taskRepository.deleteTaskById(id);
    }

    public Function<Task, TaskDto> toTaskDto() {
        return task -> {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setTitle(task.getTitle());
            taskDto.setStatus(String.valueOf(task.getStatus()));
            Optional.ofNullable(task.getProject())
                    .ifPresent(project -> taskDto.setProjectId(task.getProject().getId()));
            Optional.ofNullable(task.getEmployee())
                    .ifPresent(employee -> taskDto.setEmployeeId(task.getEmployee().getId()));
            return taskDto;
        };
    }

    public Function<TaskDto, Task> toTask() {
        return taskDto -> {
            Task task = new Task();
            task.setId(taskDto.getId());
            task.setTitle(taskDto.getTitle());
            task.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
            if(taskDto.getProjectId() != 0) {
                projectRepository.findById(taskDto.getProjectId()).
                        ifPresentOrElse(task::setProject, () -> {
                            throw new EntityNotExistsException("Project does not exist.");
                        });
            }
            if(taskDto.getEmployeeId() != 0) {
                employeeRepository.findById(taskDto.getEmployeeId()).
                        ifPresentOrElse(task::setEmployee, () -> {
                            throw new EntityNotExistsException("Employee does not exist.");
                        });
            }
            return task;
        };
    }
}
