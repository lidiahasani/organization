package com.lidia.organization.repositories;

import com.lidia.organization.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findById(int id);

    List<Task> findByEmployeeId(int id);

    @Transactional
    void deleteTaskById(int id);

}
