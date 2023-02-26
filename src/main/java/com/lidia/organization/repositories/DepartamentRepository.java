package com.lidia.organization.repositories;

import com.lidia.organization.model.Departament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DepartamentRepository extends JpaRepository<Departament, Integer> {

    Optional<Departament> findById(int id);

}
