package com.lidia.organization.repositories;

import com.lidia.organization.model.Departament;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartamentRepository extends JpaRepository<Departament, Integer> {

    Departament findById(int id);

}
