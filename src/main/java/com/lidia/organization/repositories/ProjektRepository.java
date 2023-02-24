package com.lidia.organization.repositories;

import com.lidia.organization.model.Projekt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjektRepository extends JpaRepository<Projekt, Integer> {

    Projekt findById(int id);

    @Transactional
    void deleteProjektById(int id);

}
