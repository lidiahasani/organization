package com.lidia.organization.services.api;

import com.lidia.organization.dto.PunonjesDto;

import java.util.List;

public interface PunonjesService {

    void regjistroPunonjes(PunonjesDto punonjesDto);

    PunonjesDto kerkoPunonjes(String emer);

    List<PunonjesDto> lexoPunonjes();

    void fshiPunonjes(int id);

    PunonjesDto ndryshoPunonjes(PunonjesDto punonjesDto);
}
