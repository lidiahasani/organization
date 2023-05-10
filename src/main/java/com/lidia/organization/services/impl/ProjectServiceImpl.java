package com.lidia.organization.services.impl;

import com.lidia.organization.dto.ProjectDto;
import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.model.Project;
import com.lidia.organization.model.ProjectStatus;
import com.lidia.organization.repositories.DepartmentRepository;
import com.lidia.organization.repositories.ProjectRepository;
import com.lidia.organization.services.api.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final DepartmentRepository departmentRepository;

    private final TaskServiceImpl taskService;

    public ProjectServiceImpl(ProjectRepository projectRepository, DepartmentRepository departmentRepository, TaskServiceImpl taskService) {
        this.projectRepository = projectRepository;
        this.departmentRepository = departmentRepository;
        this.taskService = taskService;
    }

    @Override
    public ProjectDto createOrUpdate(ProjectDto projectDto){
        var project = projectRepository.save(toProject().apply(projectDto));
        return toProjectDto().apply(project);
    }

    @Override
    public ProjectDto read(int id){
        return projectRepository.findById(id).map(toProjectDto())
                .orElseThrow(() -> new EntityNotExistsException("Project does not exist."));
    }

    @Override
    public List<ProjectDto> read(){
        return projectRepository.findAll().stream().map(toProjectDto()).toList();
    }

    @Override
    public void delete(int id){
        projectRepository.deleteProjectById(id);
    }

    public Function<Project, ProjectDto> toProjectDto() {
        return project -> {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(project.getId());
            projectDto.setTitle(project.getTitle());
            projectDto.setStartDate(project.getStartDate());
            projectDto.setEndDate(project.getEndDate());
            projectDto.setStatus(String.valueOf(project.getStatus()));
            Optional.ofNullable(project.getDepartment())
                    .ifPresent(department -> projectDto.setDepartmentId(project.getDepartment().getId()));
            List<TaskDto> taskDtoList = project.getTasks().stream().map(taskService.toTaskDto()).toList();
            projectDto.setTaskDtoList(taskDtoList);
            return projectDto;
        };
    }

    public Function<ProjectDto, Project> toProject() {
        return projectDto -> {
            Project project = new Project();
            project.setId(projectDto.getId());
            project.setTitle(projectDto.getTitle());
            project.setStartDate(projectDto.getStartDate());
            project.setEndDate(projectDto.getEndDate());
            project.setStatus(ProjectStatus.valueOf(projectDto.getStatus()));
            if(projectDto.getDepartmentId() != 0) {
                departmentRepository.findById(projectDto.getDepartmentId()).
                        ifPresentOrElse(project::setDepartment, () -> {
                            throw new EntityNotExistsException("Department does not exist.");
                        });
            }
            return project;
        };
    }

    @Override
    public List<ProjectDto> readAllByTitleEndingWith(String title){
        return projectRepository.findAllByTitleEndingWith(title).stream().map(toProjectDto()).toList();
    }

    @Override
    public List<ProjectDto> readAllByStartDateGreaterThan(LocalDate date){
        return projectRepository.findAllByStartDateGreaterThan(date).stream().map(toProjectDto()).toList();
    }

}
