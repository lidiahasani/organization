package com.lidia.organization.repositories;

import com.lidia.organization.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findById(int id);

}
