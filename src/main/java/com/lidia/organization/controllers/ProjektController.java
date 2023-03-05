package com.lidia.organization.controllers;

import com.lidia.organization.dto.ProjektDto;
import com.lidia.organization.services.api.ProjektService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/projekt")
public class ProjektController {

    private final ProjektService projektService;

    public ProjektController(ProjektService projektService) {
        this.projektService = projektService;
    }

    @PostMapping("/shto")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public void shtoProjekt(
            @RequestBody ProjektDto projektDto){
        projektService.shtoProjekt(projektDto);
    }

    @GetMapping("/kerko/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public ProjektDto kerkoProjekt(
            @PathVariable("id") Integer id){
        return projektService.kerkoProjekt(id);
    }

    @GetMapping("/lexo")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public List<ProjektDto> lexoProjektet(){
        return projektService.lexoProjektet();
    }

    @DeleteMapping("/fshi/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public void fshiProjekt(
            @PathVariable("id") Integer id){
        projektService.fshiProjekt(id);
    }

    @PostMapping("/ndrysho")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public void ndryshoProjekt(
            @RequestBody ProjektDto projektDto){
        projektService.ndryshoProjekt(projektDto);
    }

    @GetMapping("/lexomeKusht/{string}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public List<ProjektDto> lexoProjektmeKushtTitull(
            @PathVariable String string){
        return projektService.lexoProjektetmeKushtTitull(string);
    }

    @GetMapping("/lexomeKushtDate")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public List<ProjektDto> lexoProjektmeKushtDate(
            @RequestBody Date date){
        return projektService.lexoProjektetmeKushtDate(date);
    }
}
