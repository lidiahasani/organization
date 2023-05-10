package com.lidia.organization.repositories;

import com.lidia.organization.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByName(String name);

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findById(int id);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdIsNot(String email, int id);

    @Transactional
    void deleteEmployeeById(int id);

}
