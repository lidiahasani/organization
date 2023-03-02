package com.lidia.organization.services.api;

import com.lidia.organization.dto.ProjektDto;

import java.util.Date;
import java.util.List;

public interface ProjektService {

    void shtoProjekt(ProjektDto projektDto);

    ProjektDto kerkoProjekt(int id);

    List<ProjektDto> lexoProjektet();

    void fshiProjekt(int id);

    void ndryshoProjekt(ProjektDto projektDto);

    List<ProjektDto> lexoProjektetmeKushtTitull(String titull);

    List<ProjektDto> lexoProjektetmeKushtDate(Date date);
}
