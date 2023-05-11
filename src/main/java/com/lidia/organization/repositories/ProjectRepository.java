package com.lidia.organization.repositories;

import com.lidia.organization.model.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findById(int id);

    List<Project> findByDepartmentId(int id);

    @Transactional
    void deleteProjectById(int id);

    List<Project> findAllByTitleEndingWith(String title);

    List<Project> findAllByStartDateGreaterThan(LocalDate date);
}
