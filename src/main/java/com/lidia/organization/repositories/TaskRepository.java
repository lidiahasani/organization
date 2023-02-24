package com.lidia.organization.repositories;

import com.lidia.organization.model.Punonjes;
import com.lidia.organization.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findById(int id);

    @Transactional
    void deleteTaskById(int id);

}
