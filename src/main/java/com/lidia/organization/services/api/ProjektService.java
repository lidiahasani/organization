package com.lidia.organization.services.api;

import com.lidia.organization.dto.ProjektDto;

import java.time.LocalDate;
import java.util.List;

public interface ProjektService {

    ProjektDto shtoOseNdryshoProjekt(ProjektDto projektDto);

    ProjektDto kerkoProjekt(int id);

    List<ProjektDto> lexoProjektet();

    void fshiProjekt(int id);

    List<ProjektDto> lexoProjektetmeKushtTitull(String titull);

    List<ProjektDto> lexoProjektetmeKushtDate(LocalDate date);
}
