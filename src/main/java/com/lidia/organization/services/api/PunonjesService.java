package com.lidia.organization.services.api;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.security.dto.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PunonjesService {

    ResponseEntity<MessageResponse> regjistroPunonjes(PunonjesDto punonjesDto);

    PunonjesDto kerkoPunonjes(String emer);

    List<PunonjesDto> lexoPunonjes();

    void fshiPunonjes(int id);

    void ndryshoPunonjes(PunonjesDto punonjesDto);
}
