package com.lidia.organization.repositories;

import com.lidia.organization.model.Departament;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartamentRepository extends JpaRepository<Departament, Integer> {

    Departament findByEmer(String emer);

    Departament findById(int id);

    @Transactional
    void deleteDepartamentById(int id);

}
