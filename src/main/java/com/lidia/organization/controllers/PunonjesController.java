package com.lidia.organization.controllers;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.services.api.PunonjesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> regjistroPunonjes(
            @Valid @RequestBody PunonjesDto punonjesDto){
        punonjesService.regjistroPunonjes(punonjesDto);
        return ResponseEntity.ok("Punonjesi u regjistrua me sukses!");
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void fshiPunonjes(
            @PathVariable("id") Integer id){
        punonjesService.fshiPunonjes(id);
    }

    @PostMapping("/ndrysho")
    @PreAuthorize("hasRole('ADMIN')")
    public PunonjesDto ndryshoPunonjes(
            @Valid @RequestBody PunonjesDto punonjesDto){
        return punonjesService.ndryshoPunonjes(punonjesDto);
    }
}
