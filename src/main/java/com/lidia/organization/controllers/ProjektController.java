package com.lidia.organization.controllers;

import com.lidia.organization.dto.ProjektDto;
import com.lidia.organization.services.ProjektService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ProjektController {

    private final ProjektService projektService;

    public ProjektController(ProjektService projektService) {
        this.projektService = projektService;
    }

    @PostMapping("/projekt/shto")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public void shtoProjekt(
            @RequestBody ProjektDto projektDto){
        projektService.shtoProjekt(projektDto);
    }

    @GetMapping("/projekt/kerko/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public ProjektDto kerkoProjekt(
            @PathVariable("id") Integer id){
        return projektService.kerkoProjekt(id);
    }

    @GetMapping("/projekt/lexo")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public List<ProjektDto> lexoProjektet(){
        return projektService.lexoProjektet();
    }

    @DeleteMapping("/projekt/fshi/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public void fshiProjekt(
            @PathVariable("id") Integer id){
        projektService.fshiProjekt(id);
    }

    @PostMapping("/projekt/ndrysho")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public void ndryshoProjekt(
            @RequestBody ProjektDto projektDto){
        projektService.ndryshoProjekt(projektDto);
    }

    @GetMapping("/projekt/lexomeKusht/{string}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public List<ProjektDto> lexoProjektmeKushtTitull(
            @PathVariable String string){
        return projektService.lexoProjektetmeKushtTitull(string);
    }

    @GetMapping("/projekt/lexomeKushtDate")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER', 'PROJECT_MANAGER')")
    public List<ProjektDto> lexoProjektmeKushtDate(
            @RequestBody Date date){
        return projektService.lexoProjektetmeKushtDate(date);
    }
}
