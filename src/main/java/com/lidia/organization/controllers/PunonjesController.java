package com.lidia.organization.controllers;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.services.PunonjesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PunonjesController {

    private final PunonjesService punonjesService;

    public PunonjesController(PunonjesService punonjesService) {
        this.punonjesService = punonjesService;
    }

    @PostMapping("/punonjes/regjistro")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> regjistroPunonjes(
            @Valid @RequestBody PunonjesDto punonjesDto){
        return punonjesService.regjistroPunonjes(punonjesDto);
    }

    @GetMapping("/punonjes/kerko/{emer}")
    public PunonjesDto kerkoPunonjes(
            @PathVariable String emer){
        return punonjesService.kerkoPunonjes(emer);
    }

    @GetMapping("/punonjes/lexo")
    public List<PunonjesDto> lexoPunonjes(){
        return punonjesService.lexoPunonjes();
    }

    @DeleteMapping("/punonjes/fshi/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void fshiPunonjes(
            @PathVariable("id") Integer id){
        punonjesService.fshiPunonjes(id);
    }

    @PostMapping("/punonjes/ndrysho")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void ndryshoPunonjes(
            @Valid @RequestBody PunonjesDto punonjesDto){
        punonjesService.ndryshoPunonjes(punonjesDto);
    }
}
