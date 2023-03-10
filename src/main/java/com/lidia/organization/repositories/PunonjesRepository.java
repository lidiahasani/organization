package com.lidia.organization.repositories;

import com.lidia.organization.model.Punonjes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PunonjesRepository extends JpaRepository<Punonjes, Integer> {

    Optional<Punonjes> findByEmer(String emer);

    Optional<Punonjes> findByEmail(String email);

    Optional<Punonjes> findById(int id);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdIsNot(String email, int id);

    @Transactional
    void deletePunonjesById(int id);

}
