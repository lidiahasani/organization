package com.lidia.organization.controllers;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.services.PunonjesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PunonjesController {

    private final PunonjesService punonjesService;

    public PunonjesController(PunonjesService punonjesService) {
        this.punonjesService = punonjesService;
    }

    @PostMapping("/punonjes/shto")
    public void shtoPunonjes(
            @RequestBody PunonjesDto punonjesDto){
        punonjesService.shtoPunonjes(punonjesDto);
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
    public void fshiPunonjes(
            @PathVariable("id") Integer id){
        punonjesService.fshiPunonjes(id);
    }

    @PostMapping("/punonjes/ndrysho")
    public void ndryshoPunonjes(
            @RequestBody PunonjesDto punonjesDto){
        punonjesService.ndryshoPunonjes(punonjesDto);
    }
}
