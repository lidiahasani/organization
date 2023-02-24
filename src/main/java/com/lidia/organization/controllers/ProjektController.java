package com.lidia.organization.controllers;

import com.lidia.organization.dto.ProjektDto;
import com.lidia.organization.services.ProjektService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProjektController {

    private final ProjektService projektService;

    public ProjektController(ProjektService projektService) {
        this.projektService = projektService;
    }

    @PostMapping("/projekt/shto")
    public void shtoProjekt(
            @RequestBody ProjektDto projektDto){
        projektService.shtoProjekt(projektDto);
    }

    @GetMapping("/projekt/kerko/{id}")
    public ProjektDto kerkoProjekt(
            @PathVariable("id") Integer id){
        return projektService.kerkoProjekt(id);
    }

    @GetMapping("/projekt/lexo")
    public List<ProjektDto> lexoPunonjes(){
        return projektService.lexoProjektet();
    }

    @DeleteMapping("/projekt/fshi/{id}")
    public void fshiProjekt(
            @PathVariable("id") Integer id){
        projektService.fshiProjekt(id);
    }

    @PostMapping("/projekt/ndrysho")
    public void ndryshoProjekt(
            @RequestBody ProjektDto projektDto){
        projektService.ndryshoProjekt(projektDto);
    }
}
