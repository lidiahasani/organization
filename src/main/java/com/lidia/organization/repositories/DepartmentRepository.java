package com.lidia.organization.repositories;

import com.lidia.organization.model.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findById(int id);

    @Transactional
    void deleteDepartmentById(int id);

}
