package com.lidia.organization.services.api;

import com.lidia.organization.dto.ProjectDto;

import java.time.LocalDate;
import java.util.List;

public interface ProjectService {

    ProjectDto createOrUpdate(ProjectDto projectDto);

    ProjectDto read(int id);

    List<ProjectDto> read();

    void delete(int id);

    List<ProjectDto> readAllByTitleEndingWith(String title);

    List<ProjectDto> readAllByStartDateGreaterThan(LocalDate date);
}
