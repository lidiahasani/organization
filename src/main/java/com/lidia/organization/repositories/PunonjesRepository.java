package com.lidia.organization.repositories;

import com.lidia.organization.model.Punonjes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PunonjesRepository extends JpaRepository<Punonjes, Integer> {

    Punonjes findByEmer(String emer);

    Punonjes findById(int id);

    @Transactional
    void deletePunonjesById(int id);

}
