package com.lidia.organization.controllers;

import com.lidia.organization.dto.ProjectDto;
import com.lidia.organization.services.api.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public ProjectDto create(
            @RequestBody ProjectDto projectDto){
        return projectService.createOrUpdate(projectDto);
    }

    @GetMapping("/read/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public ProjectDto read(
            @PathVariable("id") Integer id){
        return projectService.read(id);
    }

    @GetMapping("/read")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public List<ProjectDto> read(){
        return projectService.read();
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public ProjectDto update(
            @RequestBody ProjectDto projectDto){
        return projectService.createOrUpdate(projectDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public void delete(
            @PathVariable("id") Integer id){
        projectService.delete(id);
    }

    @GetMapping("/readByTitle/{string}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public List<ProjectDto> readByTitle(
            @PathVariable String string){
        return projectService.readAllByTitleEndingWith(string);
    }

    @GetMapping("/readByDate")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public List<ProjectDto> readByDate(
            @RequestBody LocalDate date){
        return projectService.readAllByStartDateGreaterThan(date);
    }

}
