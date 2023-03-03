package com.lidia.organization.controllers;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.security.dto.MessageResponse;
import com.lidia.organization.services.api.PunonjesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/punonjes")
public class PunonjesController {

    private final PunonjesService punonjesService;

    public PunonjesController(PunonjesService punonjesService) {
        this.punonjesService = punonjesService;
    }

    @PostMapping("/regjistro")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MessageResponse> regjistroPunonjes(
            @Valid @RequestBody PunonjesDto punonjesDto){
        return punonjesService.regjistroPunonjes(punonjesDto);
    }

    @GetMapping("/kerko/{emer}")
    public PunonjesDto kerkoPunonjes(
            @PathVariable String emer){
        return punonjesService.kerkoPunonjes(emer);
    }

    @GetMapping("/lexo")
    public List<PunonjesDto> lexoPunonjes(){
        return punonjesService.lexoPunonjes();
    }

    @DeleteMapping("/fshi/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void fshiPunonjes(
            @PathVariable("id") Integer id){
        punonjesService.fshiPunonjes(id);
    }

    @PostMapping("/ndrysho")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void ndryshoPunonjes(
            @Valid @RequestBody PunonjesDto punonjesDto){
        punonjesService.ndryshoPunonjes(punonjesDto);
    }
}
