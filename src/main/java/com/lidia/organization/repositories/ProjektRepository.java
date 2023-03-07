package com.lidia.organization.repositories;

import com.lidia.organization.model.Projekt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjektRepository extends JpaRepository<Projekt, Integer> {

    Optional<Projekt> findById(int id);

    @Transactional
    void deleteProjektById(int id);

    List<Projekt> findAllByTitullEndingWith(String titull);

    List<Projekt> findAllByDataNisjeGreaterThan(LocalDate date);
}
